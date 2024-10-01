package cengiz.service;


import cengiz.dto.PersonelDto;
import cengiz.enums.PersonelRules;
import cengiz.enums.PersonelRules1;
import cengiz.exception.BaseException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class PersonelService {


  // if -else blok
  public PersonelDto save1(PersonelDto personelDto) {
    if(personelDto.getBaslangicTarihi() != null) {
      if (personelDto.getBaslangicTarihi().isBefore(LocalDateTime.now())) {
        throw new BaseException("Baslangic Tarihi gecmis zamanli olamaz");
      } else if( personelDto.getBaslangicTarihi().isAfter(personelDto.getBitisTarihi())) {
        throw new BaseException("Bitis tarihi baslangic tarihinden once olamaz ");
      } else if (personelDto.getBaslangicTarihi().plusDays(30).isBefore(personelDto.getBitisTarihi())) {
        throw new BaseException("Baslangic bitis tarihi arasinda en fazla 30 gun olabilir ");
      } else if (LocalDateTime.now().plusDays(365).isBefore(personelDto.getBitisTarihi())) {
        throw new BaseException("Bitis tarihi en fazla 365 gun sonrasi olabilir ");
      }
    } else {
      throw new BaseException("Baslangic Tarihi bos olamaz");
    }

    return personelDto;
  }
  // predicate class ile
  public PersonelDto save2(PersonelDto personelDto) {
    // Predicate ve mesajları bir arada tutan bir yapı oluşturuyoruz.
    Map<Predicate<PersonelDto>, String> rules = Map.of(
        PersonelRules1.isBaslangicTarihiNull(), "Başlangıç Tarihi boş olamaz",
        PersonelRules1.isBaslangicTarihiGecmisZamanli(), "Başlangıç Tarihi geçmiş zamanlı olamaz",
        PersonelRules1.isBitisTarihiBaslangictanOnce(), "Bitiş tarihi başlangıç tarihinden önce olamaz",
        PersonelRules1.isBaslangicBitisArasiFazlaOtuzGun(), "Başlangıç-bitiş tarihi arasında en fazla 30 gün olabilir",
        PersonelRules1.isBitisTarihiFazla365GunSonrasi(), "Bitiş tarihi en fazla 365 gün sonrası olabilir"
    );

    // Predicate'leri sırayla kontrol eder, eğer bir tanesi true ise, ilgili mesajla exception fırlatır.
    rules.entrySet().stream()
        .filter(entry -> entry.getKey().test(personelDto))
        .findFirst()
        .ifPresent(entry -> {
          throw new BaseException(entry.getValue());
        });

    return personelDto;
  }
  // predicate enum sinifi ile
  public PersonelDto save(PersonelDto personelDto) {
    // Enum'daki tüm kuralları stream ile kontrol ederiz
    Arrays.stream(PersonelRules.values())
        .filter(rule -> rule.isInvalid(personelDto))
        .findFirst()
        .ifPresent(rule -> {
          throw new BaseException(rule.getErrorMessage());
        });

    return personelDto;
  }
}
