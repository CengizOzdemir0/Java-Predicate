package cengiz.controller;

import cengiz.dto.PersonelDto;
import cengiz.service.PersonelService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personel")
@RequiredArgsConstructor
public class PersonelController {

  private final PersonelService personelService;

  @PostMapping
  public ResponseEntity<PersonelDto> save(@RequestBody PersonelDto personelDto) {
   return ResponseEntity.ok(personelService.save(personelDto));
  }


}
