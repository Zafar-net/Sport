package zafarnet.sport.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.Period;

    @Entity
    @Table(name = "users")
    @Getter
    @Setter
    @NoArgsConstructor
    public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String firstName;
        private String lastName;
        private LocalDate birthDate;

        @Transient
        private int age;

        @Transient
        private int ageGroup;

        public void User(String firstName, String lastName, LocalDate birthDate) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.birthDate = birthDate;
            this.age = calculateAge();
            this.ageGroup = determineAgeGroup();
        }

        public int calculateAge() {
            return Period.between(this.birthDate, LocalDate.now()).getYears();
        }

        public int determineAgeGroup() {
            int age = calculateAge();
            if (age >= 18 && age <= 25) return 1;
            else if (age >= 26 && age <= 30) return 2;
            else if (age >= 31 && age <= 35) return 3;
            else if (age >= 36 && age <= 40) return 4;
            else return 5; // 41 va undan katta yoshdagilar
        }
    }