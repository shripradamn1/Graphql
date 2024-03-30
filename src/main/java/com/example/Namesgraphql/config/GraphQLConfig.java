package com.example.Namesgraphql.config;

import com.example.Namesgraphql.resolver.BookRepository;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.IOException;
import java.util.stream.Stream;

@Configuration
public class GraphQLConfig {

    private final BookRepository bookRepository;

    public GraphQLConfig(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Bean
    public GraphQL graphQL(GraphQLSchema graphQLSchema) {
        return GraphQL.newGraphQL(graphQLSchema).build();
    }

    @Bean
    public GraphQLSchema graphQLSchema() throws IOException {
        TypeDefinitionRegistry typeRegistry = new TypeDefinitionRegistry();
        Stream.of("classpath:schema.graphql")
                .map(resource -> getClass().getClassLoader().getResourceAsStream(resource))
                .forEach(stream -> typeRegistry.merge(new SchemaParser().parse(stream)));

        RuntimeWiring wiring = buildWiring();

        return new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", builder -> builder
                        .dataFetcher("allBooks", env -> bookRepository.allBooks())
                        .dataFetcher("bookById", env -> {
                            int id = env.getArgument("id");
                            return bookRepository.bookById(id);
                        }))
                .build();
    }
}
