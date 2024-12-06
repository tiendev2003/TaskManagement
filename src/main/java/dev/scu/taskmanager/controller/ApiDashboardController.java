package dev.scu.taskmanager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import dev.scu.taskmanager.model.User;
import dev.scu.taskmanager.repository.TaskRepository;
import dev.scu.taskmanager.repository.UserRepository;

@RestController
public class ApiDashboardController {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/api/statistics")
    @ResponseBody
    public Map<String, Object> getStatistics() throws JsonProcessingException {
        long completedTasks = taskRepository.countByIsCompleted(true);
        long inProgressTasks = taskRepository.countByIsCompleted(false);
        long totalTasks = taskRepository.count();
        long pendingTasks = totalTasks - completedTasks - inProgressTasks;

        List<User> users = userRepository.findAll();
        long totalUsers = users.size();
        Map<String, Long> taskCountByUser = users.stream()
            .collect(Collectors.toMap(User::getName, user -> (long) user.getTasksOwned().size()));

        Map<String, Object> response = new HashMap<>();
        response.put("completedTasks", completedTasks);
        response.put("inProgressTasks", inProgressTasks);
        response.put("totalTasks", totalTasks);
        response.put("pendingTasks", pendingTasks);
        response.put("totalUsers", totalUsers);
        response.put("taskCountByUser", taskCountByUser);

        return response;
    }
}
