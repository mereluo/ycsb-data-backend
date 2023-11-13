package com.test.datamanagement.controller;

import com.test.datamanagement.entity.DBConfig;
import com.test.datamanagement.service.DBConfigService;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

  @GetMapping("/{id}")
  public Optional<DBConfig> findEntityById(@PathVariable("id") Long id) {
    return DBConfigService.findById(id);
  }

  @GetMapping("/description/{description}")
  public DBConfig findEntityByDescription(@PathVariable("description") String description) {
    return DBConfigService.findByDescription(description);
  }
  @PostMapping("")
  public DBConfig saveEntity(@RequestBody DBConfig dbConfig) {
    DBConfig entity =  DBConfigService.findByDescription(dbConfig.getDescription());
    if (entity!= null && entity.equals(dbConfig)) {
      return entity;
    }
    return DBConfigService.saveEntity(dbConfig);
  }
  @PutMapping
  public DBConfig updateEntity(@RequestBody DBConfig dbConfig) {
    return DBConfigService.updateEntity(dbConfig);
  }
  @DeleteMapping("/{id}")
  public void deleteEntity(@PathVariable("id") Long id) {
    DBConfigService.deleteEntity(id);
  }
}
