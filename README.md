# SkillsJars Spring AI Example

This example shows how to use the Spring AI [Agent Utils](https://github.com/spring-ai-community/spring-ai-agent-utils) library with [SkillsJars](https://www.skillsjars.com) to give an AI Agent access to dependency-defined Agent Skills.

The example uses the [browser-use](https://github.com/browser-use/browser-use) Agent Skill.

## Running the Example

1. [Create a Bedrock API key](https://us-east-1.console.aws.amazon.com/bedrock/home?region=us-east-1#/api-keys/long-term/create)
2. Run the application
    ```bash
    export AWS_BEARER_TOKEN_BEDROCK=<YOUR BEDROCK API KEY>
    ./mvnw spring-boot:run
    ```
3. Ask a question like `Get the contents of https://www.skillsjars.com`
