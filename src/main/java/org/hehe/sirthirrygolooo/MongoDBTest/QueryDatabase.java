package org.hehe.sirthirrygolooo.MongoDBTest;

import com.mongodb.*;
import com.mongodb.reactivestreams.client.*;
import org.bson.Document;
import reactor.core.publisher.Mono;
import io.github.cdimascio.dotenv.Dotenv;

import static com.mongodb.client.model.Filters.eq;

public class QueryDatabase {
    public static void main(String[] args) {
        // Charger les variables depuis le .env
        Dotenv dotenv = Dotenv.load();

        String username = dotenv.get("DB_USERNAME");
        String password = dotenv.get("DB_PASSWORD");

        String uri = "mongodb+srv://" + username + ":" + password + "@clustersamsoul.ku08vkx.mongodb.net/?appName=ClusterSamsoul";

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(uri))
                .serverApi(serverApi)
                .build();

        try (MongoClient mongoClient = MongoClients.create(settings)) {
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> movies = database.getCollection("movies");

            Mono.from(movies.find(eq("title", "Back to the Future")))
                    .doOnSuccess(i -> System.out.println(i))
                    .doOnError(err -> System.out.println("Error: " + err.getMessage()))
                    .block();
        }
    }
}
