package com.travelapp.api.graph.service;

import com.travelapp.api.users.userfollows.entity.UserFollows;
import com.travelapp.api.users.userfollows.repository.UserFollowsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.*;

@Service
public class UserGraphService {

    @Autowired
    private UserFollowsRepository userFollowsRepository;

    // In-memory graph: key = follower's userUid, value = set of userUids that they follow.
    private Map<String, Set<String>> userGraph = new HashMap<>();

    @PostConstruct
    public void initializeGraph() {
        refreshGraph();
    }

    /**
     * Refreshes the in-memory graph from the user_follows data.
     */
    public synchronized void refreshGraph() {
        Map<String, Set<String>> graph = new HashMap<>();
        List<UserFollows> follows = userFollowsRepository.findAll();
        for (UserFollows uf : follows) {
            String followerUid = uf.getFollower().getUserUid();
            String followingUid = uf.getFollowing().getUserUid();
            graph.computeIfAbsent(followerUid, k -> new HashSet<>()).add(followingUid);
        }
        userGraph = graph;
    }

    /**
     * Returns the current in-memory graph.
     */
    public Map<String, Set<String>> getGraph() {
        return userGraph;
    }

    /**
     * Computes the degree of separation for all nodes reachable from startUid up to maxDegree.
     */
    public Map<String, Integer> getDegreesOfSeparation(String startUid, int maxDegree) {
        Map<String, Integer> degrees = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        degrees.put(startUid, 0);
        queue.offer(startUid);
        
        while (!queue.isEmpty()) {
            String current = queue.poll();
            int currentDegree = degrees.get(current);
            if (currentDegree >= maxDegree) continue;
            for (String neighbor : userGraph.getOrDefault(current, Collections.emptySet())) {
                if (!degrees.containsKey(neighbor)) {
                    degrees.put(neighbor, currentDegree + 1);
                    queue.offer(neighbor);
                }
            }
        }
        return degrees;
    }
    
    /**
     * Finds the shortest path between startUid and endUid using BFS.
     */
    public List<String> findShortestPath(String startUid, String endUid) {
        if (!userGraph.containsKey(startUid)) {
            return Collections.emptyList();
        }
        Queue<String> queue = new LinkedList<>();
        Map<String, String> parent = new HashMap<>();
        Set<String> visited = new HashSet<>();
    
        visited.add(startUid);
        queue.offer(startUid);
    
        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(endUid)) {
                LinkedList<String> path = new LinkedList<>();
                for (String node = endUid; node != null; node = parent.get(node)) {
                    path.addFirst(node);
                }
                return path;
            }
            for (String neighbor : userGraph.getOrDefault(current, Collections.emptySet())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, current);
                    queue.offer(neighbor);
                }
            }
        }
        return Collections.emptyList();
    }

    /**
     * Retrieves all nodes reachable from a given start user.
     */
    public Set<String> getReachableNodes(String startUid) {
        Set<String> reachable = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        
        queue.offer(startUid);
        reachable.add(startUid);

        while(!queue.isEmpty()){
            String current = queue.poll();
            for(String neighbor : userGraph.getOrDefault(current, Collections.emptySet())){
                if(!reachable.contains(neighbor)){
                    reachable.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return reachable;
    }
}

