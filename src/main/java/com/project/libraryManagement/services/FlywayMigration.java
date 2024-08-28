package com.project.libraryManagement.services;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FlywayMigration implements FlywayMigrationStrategy {

    @Override
    public void migrate(Flyway flyway) {
        Boolean isValid = flyway.validateWithResult().invalidMigrations.isEmpty();
        if (isValid) {
            onMigrate(flyway);
        }
        else {
            flyway.repair();
            onMigrate(flyway);
        }
    }

    private void onMigrate(Flyway flyway) {
        try {
            flyway.migrate();
        }
        catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            // flyway.undo();
        }
    }
    
}
