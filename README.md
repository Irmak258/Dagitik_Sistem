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

