package com.test.datamanagement.controller;

import com.test.datamanagement.entity.DatabaseOption;
import com.test.datamanagement.service.DBOptionService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/dbOption")
public class DBOptionController {
  private final DBOptionService DBOptionService;

  public DBOptionController(DBOptionService DBOptionService) {
    this.DBOptionService = DBOptionService;
  }
  @GetMapping("")
  public List<DatabaseOption> findAllEntity() {
    return DBOptionService.findAllEntity();
  }
  @GetMapping("/name/{name}")
  public DatabaseOption findFirstByDatabase(@PathVariable("name") String name) {
    return DBOptionService.findFirstByDatabase(name);
  }
  @PostMapping
  public DatabaseOption saveEntity(@RequestBody DatabaseOption dbOption) {
    return DBOptionService.saveEntity(dbOption);
  }
}
