package com.test.datamanagement.controller;

import com.test.datamanagement.entity.DatabaseOption;
import com.test.datamanagement.service.DatabaseOptionService;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class DatabaseOptionController {
  private final DatabaseOptionService databaseOptionService;

  public DatabaseOptionController(DatabaseOptionService databaseOptionService) {
    this.databaseOptionService = databaseOptionService;
  }

  @GetMapping("")
  public List<DatabaseOption> findAllEntity() {
    return databaseOptionService.findAllEntity();
  }

  @GetMapping("/{id}")
  public Optional<DatabaseOption> findEntityById(@PathVariable("id") Long id) {
    return databaseOptionService.findById(id);
  }

  @PostMapping
  public DatabaseOption saveEntity(@RequestBody DatabaseOption databaseOption) {
    return databaseOptionService.saveEntity(databaseOption);
  }

  @PutMapping
  public DatabaseOption updateEntity(@RequestBody DatabaseOption databaseOption) {
    return databaseOptionService.updateEntity(databaseOption);
  }

  @DeleteMapping("/{id}")
  public void deleteEntity(@PathVariable("id") Long id) {
    databaseOptionService.deleteEntity(id);
  }
}
