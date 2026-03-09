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
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ChatClient.Builder chatClientBuilder) {

        return args -> {
            var skillsTool = SkillsTool.builder()
                    .addSkillsResource(new ClassPathResource("/META-INF/skills")).build();
            System.out.println(skillsTool.getToolDefinition());
            System.out.println(skillsTool.getToolMetadata());

            ChatClient chatClient = chatClientBuilder
//                    .defaultSystem("When calling Skills, the toolname is \"Skill\"")
                    .defaultToolCallbacks(skillsTool)
                    .defaultAdvisors(
                            ToolCallAdvisor.builder()
                                    .conversationHistoryEnabled(true)
                                    .build(),
                            MyLoggingAdvisor.builder().build()
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
