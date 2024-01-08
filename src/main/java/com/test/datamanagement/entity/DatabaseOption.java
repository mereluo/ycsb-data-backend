package com.test.datamanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;
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

  private String database;

  public DatabaseOption(String database) {
    this.database = database;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DatabaseOption that = (DatabaseOption) o;
    return Objects.equals(getId(), that.getId()) && Objects.equals(getDatabase(),
        that.getDatabase());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getDatabase());
  }
}
