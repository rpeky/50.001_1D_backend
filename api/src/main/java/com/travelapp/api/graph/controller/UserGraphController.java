package com.travelapp.api.graph.controller;

import com.travelapp.api.graph.service.UserGraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/graph")
public class UserGraphController {

    @Autowired
    private UserGraphService userGraphService;

    /**
     * Returns the shortest path between two users.
     * Example: GET /graph/path?start=userA&end=userB
     */
    @GetMapping("/path")
    public ResponseEntity<List<String>> getShortestPath(@RequestParam String start,
                                                        @RequestParam String end) {
        List<String> path = userGraphService.findShortestPath(start, end);
        if (path.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(path);
    }

    /**
     * Returns all users reachable from the start user.
     * Example: GET /graph/reachable?start=userA
     */
    @GetMapping("/reachable")
    public ResponseEntity<Set<String>> getReachableNodes(@RequestParam String start) {
        Set<String> reachable = userGraphService.getReachableNodes(start);
        return ResponseEntity.ok(reachable);
    }

    /**
     * Refreshes the in-memory graph.
     * Example: POST /graph/refresh
     */
    @PostMapping("/refresh")
    public ResponseEntity<String> refreshGraph() {
        userGraphService.refreshGraph();
        return ResponseEntity.ok("Graph has been refreshed.");
    }
}

