package cengiz.enums;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import cengiz.dto.PersonelDto;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum PersonelRules {

  BASLANGIC_TARIHI_NULL(
      personelDto -> personelDto.getBaslangicTarihi() == null,
      "Başlangıç Tarihi boş olamaz"
  ),

  BASLANGIC_TARIHI_GECMIS_ZAMANLI(
      personelDto -> personelDto.getBaslangicTarihi() != null && personelDto.getBaslangicTarihi().isBefore(LocalDateTime.now()),
      "Başlangıç Tarihi geçmiş zamanlı olamaz"
  ),

  BITIS_TARIHI_BASLANGICTAN_ONCE(
      personelDto -> personelDto.getBaslangicTarihi() != null && personelDto.getBitisTarihi() != null
          && personelDto.getBaslangicTarihi().isAfter(personelDto.getBitisTarihi()),
      "Bitiş tarihi başlangıç tarihinden önce olamaz"
  ),

  BASLANGIC_BITIS_ARASI_OTUZ_GUNDEN_FAZLA(
      personelDto -> personelDto.getBaslangicTarihi() != null && personelDto.getBitisTarihi() != null
          && personelDto.getBaslangicTarihi().plusDays(30).isBefore(personelDto.getBitisTarihi()),
      "Başlangıç-bitiş tarihi arasında en fazla 30 gün olabilir"
  ),

  BITIS_TARIHI_365_GUNDEN_FAZLA(
      personelDto -> personelDto.getBitisTarihi() != null && LocalDateTime.now().plusDays(365).isBefore(personelDto.getBitisTarihi()),
      "Bitiş tarihi en fazla 365 gün sonrası olabilir"
  );

  private final Predicate<PersonelDto> predicate;
  private final String errorMessage;

  public boolean isInvalid(PersonelDto dto) {
    return predicate.test(dto);
  }
}
