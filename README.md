Softmax Action Selection ile Dağıtık Sistemlerde Yük Dengeleyici (Load Balancer)
Bu proje, K adet farklı sunucudan oluşan bir kümeye gelen istekleri, sunucuların zamanla değişen (non-stationary) performans verilerine dayanarak optimize eden bir istemci taraflı yük dengeleyici simülasyonudur.

Klasik Round-Robin veya Random algoritmalarının aksine, bu projede Softmax Action Selection algoritması kullanılmıştır. Algoritma, sunucuların yanıt sürelerini (latency) birer "ödül" (reward) sinyali olarak kabul eder ve düşük gecikme süresine sahip sunucuyu seçme olasılığını artırırken, diğer sunucuları da performans değişimlerini takip etmek adına "keşfetmeye" (exploration) devam eder.

Teknik Detaylar
Dil: Java
Algoritma: Softmax Action Selection
Nümerik Stabilite: Üstel fonksiyon hesaplamalarında oluşabilecek Overflow (taşma) hatalarını önlemek amacıyla Max-Trick yöntemi uygulanmıştır.
Karmaşıklık Analizi: Algoritmanın zaman karmaşıklığı O(K)'dır (K = sunucu sayısı).

Neden Softmax?
Greedy yaklaşım, sadece o anki en iyi seçeneğe odaklanır. Ancak bu projede olduğu gibi sunucu performanslarının gürültülü ve değişken olduğu senaryolarda:
Round-Robin: Sunucu durumlarını görmezden gelir, körleme seçim yapar.
Greedy: Erken aşamada takılıp kalabilir, iyileşen sunucuları fark edemez.
Softmax: Olasılıksal yaklaşımı sayesinde en iyi sunucuyu sömürürken (exploitation), diğerlerini de periyodik olarak kontrol eder (exploration).

LoadBalancerSimulation.java dosyasını bir java programında çalıştırarak açabilirsiniz.

Çıktı Örneği:

--- Java Softmax Load Balancer Simülasyonu ---
İstek 20: Sunucu 1 seçildi. Anlık Gecikme: 24,88ms
İstek 40: Sunucu 1 seçildi. Anlık Gecikme: 12,54ms
İstek 60: Sunucu 1 seçildi. Anlık Gecikme: 23,52ms
İstek 80: Sunucu 1 seçildi. Anlık Gecikme: 23,80ms
İstek 100: Sunucu 1 seçildi. Anlık Gecikme: 25,69ms

--- SONUÇ ---
Ortalama Gecikme: 21,21ms
Analiz: Softmax, performansı yüksek olan sunucuya daha fazla ağırlık verdi.

Process finished with exit code 0



