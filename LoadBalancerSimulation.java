import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Server {
    int id;
    double baseLatency;
    double currentLatency;
    Random random = new Random();

    public Server(int id, double baseLatency) {
        this.id = id;
        this.baseLatency = baseLatency;
        this.currentLatency = baseLatency;
    }

    public double processRequest() {
        double noise = (random.nextDouble() - 0.5) * 15;
        this.currentLatency = Math.max(5, this.baseLatency + noise);
        return this.currentLatency;
    }
}

public class LoadBalancerSimulation {

    public static Server selectServerSoftmax(List<Server> servers, double temperature) {
        int n = servers.size();
        double[] rewards = new double[n];

        for (int i = 0; i < n; i++) {
            rewards[i] = -servers.get(i).currentLatency;
        }

        double maxReward = Double.NEGATIVE_INFINITY;
        for (double r : rewards) {
            if (r > maxReward) maxReward = r;
        }

        double sumExp = 0;
        double[] expRewards = new double[n];
        for (int i = 0; i < n; i++) {
            expRewards[i] = Math.exp((rewards[i] - maxReward) / temperature);
            sumExp += expRewards[i];
        }

        double rand = new Random().nextDouble();
        double cumulative = 0;
        for (int i = 0; i < n; i++) {
            double prob = expRewards[i] / sumExp;
            cumulative += prob;
            if (rand <= cumulative) return servers.get(i);
        }
        return servers.get(0);
    }

    public static void main(String[] args) {

        List<Server> cluster = new ArrayList<>();
        cluster.add(new Server(1, 20.0));
        cluster.add(new Server(2, 50.0));
        cluster.add(new Server(3, 100.0));
        double temperature = 10.0;
        double totalLatency = 0;

        System.out.println("--- Java Softmax Load Balancer Simülasyonu ---");

        for (int i = 1; i <= 100; i++) {
            Server selected = selectServerSoftmax(cluster, temperature);
            double latency = selected.processRequest();
            totalLatency += latency;

            if (i % 20 == 0) {
                System.out.printf("İstek %d: Sunucu %d seçildi. Anlık Gecikme: %.2fms%n",
                        i, selected.id, latency);
            }
        }

        System.out.println("\n--- SONUÇ ---");
        System.out.printf("Ortalama Gecikme: %.2fms%n", (totalLatency / 100));
        System.out.println("Analiz: Softmax, performansı yüksek olan sunucuya daha fazla ağırlık verdi.");
    }
}