package cengiz.enums;

import cengiz.dto.PersonelDto;
import java.time.LocalDateTime;
import java.util.function.Predicate;



public class PersonelRules1 {

  public static Predicate<PersonelDto> isBaslangicTarihiGecmisZamanli() {
    return dto -> dto.getBaslangicTarihi() != null && dto.getBaslangicTarihi().isBefore(LocalDateTime.now());
  }

  public static Predicate<PersonelDto> isBitisTarihiBaslangictanOnce() {
    return dto -> dto.getBitisTarihi() != null && dto.getBaslangicTarihi() != null && dto.getBaslangicTarihi().isAfter(dto.getBitisTarihi());
  }

  public static Predicate<PersonelDto> isBaslangicBitisArasiFazlaOtuzGun() {
    return dto -> dto.getBaslangicTarihi() != null && dto.getBitisTarihi() != null
        && dto.getBaslangicTarihi().plusDays(30).isBefore(dto.getBitisTarihi());
  }

  public static Predicate<PersonelDto> isBitisTarihiFazla365GunSonrasi() {
    return dto -> dto.getBitisTarihi() != null && LocalDateTime.now().plusDays(365).isBefore(dto.getBitisTarihi());
  }

  public static Predicate<PersonelDto> isBaslangicTarihiNull() {
    return dto -> dto.getBaslangicTarihi() == null;
  }

}
