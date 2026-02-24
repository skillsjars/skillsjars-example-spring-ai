package org.springaicommunity.agent;

import java.util.List;
import java.util.Scanner;

import org.springaicommunity.agent.tools.FileSystemTools;
import org.springaicommunity.agent.tools.ShellTools;
import org.springaicommunity.agent.tools.SkillsTool;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.ToolCallAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            ChatClient.Builder chatClientBuilder,
            @Value("${agent.skills.paths}") List<Resource> skillPaths
    ) {

        return args -> {
            ChatClient chatClient = chatClientBuilder
                    .defaultSystem("When calling Skills, the toolname is \"Skill\"")
                    .defaultToolCallbacks(
                            SkillsTool.builder().addSkillsResources(skillPaths).build()
                    )
                    .defaultTools(
                            ShellTools.builder().build(),
                            FileSystemTools.builder().build()
                    )
                    .defaultAdvisors(
                            ToolCallAdvisor.builder()
                                    .conversationHistoryEnabled(true)
                                    .build(),
                            MyLoggingAdvisor.builder()
                                    .showAvailableTools(true)
                                    .showSystemMessage(true)
                                    .build()
                    )
                    .build();

            System.out.println("\nI am your assistant.\n");

            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    System.out.print("\n> USER: ");
                    System.out.println("\n> ASSISTANT: " + chatClient.prompt(scanner.nextLine()).call().content());
                }
            }
        };
    }
}
