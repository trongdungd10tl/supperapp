package com.example.supperapp.service;

import com.example.supperapp.config.RabbitMQConfig;
import com.example.supperapp.model.Role;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleMessagePublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendRoleMessage(String message) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                message
        );
    }

    // ✅ THÊM phương thức này để dùng trong RoleServiceImpl
    public void sendRoleAction(String action, Role role) {
        String message = action.toUpperCase() + ": " + role.getRole_name();
        sendRoleMessage(message);
    }
}
