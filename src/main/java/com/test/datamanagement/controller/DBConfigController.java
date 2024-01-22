package com.test.datamanagement.controller;

import com.test.datamanagement.entity.DBConfig;
import com.test.datamanagement.service.DBConfigService;
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
@RequestMapping("/api/dbConfig")
public class DBConfigController {
  private final DBConfigService DBConfigService;

  public DBConfigController(DBConfigService dbConfigService) {
    this.DBConfigService = dbConfigService;
  }
  @GetMapping("")
  public List<DBConfig> findAllEntity() {
    return DBConfigService.findAllEntity();
  }

  @GetMapping("/description/{description}")
  public DBConfig findEntityByDescription(@PathVariable("description") String description) {
    return DBConfigService.findByDescription(description);
  }
  @PostMapping("")
  public DBConfig saveEntity(@RequestBody DBConfig dbConfig) {
    return DBConfigService.saveEntity(dbConfig);
  }
}
