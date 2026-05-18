package com.BamePlastic.backend.controller;

import com.BamePlastic.backend.entity.Player;
import com.BamePlastic.backend.repository.PlayerRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private final PlayerRepository playerRepository;

    public TestController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    // Save a player to DB
    @GetMapping("/save")
    public Player savePlayer() {
        long timestamp = System.currentTimeMillis();
        Player player = new Player();
        player.setUsername("testuser_" + timestamp);
        player.setEmail("test_" + timestamp + "@bameplastic.com");
        player.setPassword("123456");
        player.setRole("DRIVER");
        return playerRepository.save(player);
    }

    // Get all players from DB
    @GetMapping("/players")
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }
}
