package com.test.datamanagement.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name="database_option")
public class DatabaseOption {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // One DatabaseOption -> many DBConfig entities
  @OneToMany(mappedBy = "databaseOption",
      cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<DBConfig> dbConfigs;

  private String database;
}
