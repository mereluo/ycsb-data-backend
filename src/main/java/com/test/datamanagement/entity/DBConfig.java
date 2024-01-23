package com.test.datamanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name="db_config")
public class DBConfig {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private boolean isTransactional; // ycsb or ycsb-t
  private String platform;
  private int numOfNodes;
  private boolean isMultiRegion;
  private int numOfRegions;
  private String description;

  // Many DBConfig entities belong to one DatabaseOption
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "fk_database_id") // This is the foreign key column in the db_config table
  private DatabaseOption databaseOption;

  public DBConfig(boolean isTransactional, String platform, int numOfNodes, boolean isMultiRegion,
      int numOfRegions, String description, DatabaseOption databaseOption) {
    this.isTransactional = isTransactional;
    this.platform = platform;
    this.numOfNodes = numOfNodes;
    this.isMultiRegion = isMultiRegion;
    this.numOfRegions = numOfRegions;
    this.description = description;
    this.databaseOption = databaseOption;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DBConfig dbConfig = (DBConfig) o;
    return isTransactional() == dbConfig.isTransactional()
        && getNumOfNodes() == dbConfig.getNumOfNodes()
        && isMultiRegion() == dbConfig.isMultiRegion()
        && getNumOfRegions() == dbConfig.getNumOfRegions()
        && Objects.equals(getPlatform(), dbConfig.getPlatform())
        && Objects.equals(getDescription(), dbConfig.getDescription())
        && Objects.equals(getDatabaseOption(), dbConfig.getDatabaseOption());
  }

  @Override
  public int hashCode() {
    return Objects.hash(isTransactional(), getPlatform(), getNumOfNodes(), isMultiRegion(),
        getNumOfRegions(), getDescription(), getDatabaseOption());
  }
}
