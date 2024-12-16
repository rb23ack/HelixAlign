import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class MammalsBackend {
    public static void main(String[] args) throws Exception {
        // Create HTTP server
        HttpServer server = HttpServer.create(new java.net.InetSocketAddress(8080), 0);
        server.createContext("/mammals", new MammalsHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server started on port 8080...");
    }

    static class MammalsHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            List<Mammal> mammals = getMammals(); // Fetch the list of 100 mammals
            String response = new Gson().toJson(mammals);

            // Set response headers
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, response.getBytes().length);

            // Write the response body
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        // Method to return a list of 
        mammals with DNA sequences
        private List<Mammal> getMammals() {
            List<Mammal> mammals = new ArrayList<>();

            mammals.add(new Mammal("Elephant", "Loxodonta", "Savannah", "Herbivore", "60-70 years", "ATGCATGCGTACGTA"));
            mammals.add(new Mammal("Lion", "Panthera leo", "Savannah", "Carnivore", "12-16 years", "GTCGATGCACTGCGTA"));
            mammals.add(new Mammal("Tiger", "Panthera tigris", "Rainforest", "Carnivore", "10-15 years", "ACTGCGTATGCGTACG"));
            mammals.add(new Mammal("Leopard", "Panthera pardus", "Grasslands", "Carnivore", "12-17 years", "TGCATGCTAGCTAGCT"));
            mammals.add(new Mammal("Giraffe", "Giraffa camelopardalis", "Savannah", "Herbivore", "25 years", "AGCTAGCTAGTACGAT"));
            mammals.add(new Mammal("Zebra", "Equus zebra", "Savannah", "Herbivore", "25 years", "CGTACGTAGCTAGCTA"));
            mammals.add(new Mammal("Koala", "Phascolarctos cinereus", "Woodlands", "Herbivore", "10-12 years", "GATCGTAGCTAGTACG"));
            mammals.add(new Mammal("Kangaroo", "Macropus rufus", "Outback", "Herbivore", "6-8 years", "ATGCGTAGCTAGCGTA"));
            mammals.add(new Mammal("Panda", "Ailuropoda melanoleuca", "Bamboo Forest", "Herbivore", "20 years", "ACTGCGTACGGTAGCA"));
            mammals.add(new Mammal("Polar Bear", "Ursus maritimus", "Arctic", "Carnivore", "25 years", "GTACGATGCAATCGTA"));
            mammals.add(new Mammal("Grizzly Bear", "Ursus arctos", "Forests", "Omnivore", "25 years", "CGTACGTAGCATGCTA"));
            mammals.add(new Mammal("Rhinoceros", "Rhinocerotidae", "Grasslands", "Herbivore", "40-50 years", "ATGCATGCTAGCTAGC"));
            mammals.add(new Mammal("Bison", "Bison bison", "Plains", "Herbivore", "15-20 years", "GATCGTACGTAGTCGA"));
            mammals.add(new Mammal("Cheetah", "Acinonyx jubatus", "Savannah", "Carnivore", "10-12 years", "TGCATGCGTAGCGATG"));
            mammals.add(new Mammal("Jaguar", "Panthera onca", "Rainforest", "Carnivore", "12-15 years", "ACTGCGTACGTAGTGC"));
            mammals.add(new Mammal("Hippo", "Hippopotamus amphibius", "Rivers", "Herbivore", "40-50 years", "ATGCATGCTGCTAGTG"));
            mammals.add(new Mammal("Wolverine", "Gulo gulo", "Arctic", "Omnivore", "10-15 years", "GATCGTAGCTAGCGTA"));
            mammals.add(new Mammal("Red Fox", "Vulpes vulpes", "Forests", "Omnivore", "5-8 years", "ATGCTAGCGTACGATG"));
            mammals.add(new Mammal("Wolf", "Canis lupus", "Forests", "Carnivore", "6-8 years", "TGCTGATCGTACGTAC"));
            mammals.add(new Mammal("Coyote", "Canis latrans", "Grasslands", "Carnivore", "6-8 years", "GCTAGCTAGCGATGCT"));
            mammals.add(new Mammal("Elephant Seal", "Mirounga", "Beaches", "Carnivore", "20-25 years", "ACTGCGTAGCTAGCTG"));
            mammals.add(new Mammal("Orangutan", "Pongo", "Rainforest", "Omnivore", "35-45 years", "ATGCGTACGCTAGCTA"));
            mammals.add(new Mammal("Chimpanzee", "Pan troglodytes", "Forests", "Omnivore", "33 years", "GTAGCTAGCTGATGTA"));
            mammals.add(new Mammal("Gorilla", "Gorilla", "Rainforest", "Herbivore", "35-40 years", "ATGCATGCGTAGCTA"));
            mammals.add(new Mammal("Bonobo", "Pan paniscus", "Rainforest", "Omnivore", "40 years", "CGTACGTAGCTAAGCA"));
            mammals.add(new Mammal("Sloth", "Folivora", "Rainforest", "Herbivore", "20-30 years", "TGCATGCAGCTAGCTA"));
            mammals.add(new Mammal("Arctic Fox", "Vulpes lagopus", "Arctic", "Omnivore", "3-6 years", "GTCATGCATGCGTA"));
            mammals.add(new Mammal("Moose", "Alces alces", "Forests", "Herbivore", "15-20 years", "ACTGCTGACGTAAGTC"));
            mammals.add(new Mammal("Camel", "Camelus", "Desert", "Herbivore", "40-50 years", "GTAGCTAGCTAGCGTA"));
            mammals.add(new Mammal("Lynx", "Lynx", "Forests", "Carnivore", "12-15 years", "ATCGTAGCTAGCTGCA"));
            mammals.add(new Mammal("Muskox", "Ovibos moschatus", "Tundra", "Herbivore", "12-20 years", "CGATGCTAGCGTAGCT"));
            mammals.add(new Mammal("Bengal Tiger", "Panthera tigris tigris", "Rainforest", "Carnivore", "10-15 years", "ATGCGTAGCTAGCTGA"));
            mammals.add(new Mammal("Snow Leopard", "Panthera uncia", "Mountain", "Carnivore", "10-12 years", "CTAGCTGCGTAGCATG"));
            mammals.add(new Mammal("Mandrill", "Mandrillus sphinx", "Rainforest", "Omnivore", "45 years", "ATGCTAGCTAGCGTAG"));
            mammals.add(new Mammal("Aardvark", "Orycteropus afer", "Grasslands", "Insectivore", "23 years", "GCTAGCATGATCGCTA"));
            mammals.add(new Mammal("Tapir", "Tapiridae", "Rainforest", "Herbivore", "25-30 years", "ACTGCGTACGATGCTG"));
            mammals.add(new Mammal("Ocelot", "Leopardus pardalis", "Rainforest", "Carnivore", "7-10 years", "AGCTAGCTGATGCGTA"));
            mammals.add(new Mammal("Raccoon", "Procyon lotor", "Forests", "Omnivore", "2-3 years", "CGTACGATGACTGCTA"));
            mammals.add(new Mammal("Armadillo", "Dasypodidae", "Grasslands", "Omnivore", "15-20 years", "GCTAGCTAGCTAGCGT"));
            mammals.add(new Mammal("Meerkat", "Suricata suricatta", "Desert", "Insectivore", "6-14 years", "TGCATGCATGCGATCG"));
            mammals.add(new Mammal("Anteater", "Myrmecophaga tridactyla", "Rainforest", "Insectivore", "15 years", "GTCATAGCTAGTCGTA"));
            mammals.add(new Mammal("Pangolin", "Manis", "Forests", "Insectivore", "20 years", "ACTGCGTACGTAGCTA"));

            

            return mammals;
        }
    }

    static class Mammal {
        private String name;
        private String species;
        private String habitat;
        private String diet;
        private String lifespan;
        private String dnaSequence;

        public Mammal(String name, String species, String habitat, String diet, String lifespan, String dnaSequence) {
            this.name = name;
            this.species = species;
            this.habitat = habitat;
            this.diet = diet;
            this.lifespan = lifespan;
            this.dnaSequence = dnaSequence;
        }

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSpecies() {
            return species;
        }

        public void setSpecies(String species) {
            this.species = species;
        }

        public String getHabitat() {
            return habitat;
        }

        public void setHabitat(String habitat) {
            this.habitat = habitat;
        }

        public String getDiet() {
            return diet;
        }

        public void setDiet(String diet) {
            this.diet = diet;
        }

        public String getLifespan() {
            return lifespan;
        }

        public void setLifespan(String lifespan) {
            this.lifespan = lifespan;
        }

        public String getDnaSequence() {
            return dnaSequence;
        }

        public void setDnaSequence(String dnaSequence) {
            this.dnaSequence = dnaSequence;
        }
    }
}
