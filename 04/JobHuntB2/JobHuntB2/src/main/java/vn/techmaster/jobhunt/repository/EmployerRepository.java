package vn.techmaster.jobhunt.repository;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import vn.techmaster.jobhunt.model.Employer;

@Repository
public class EmployerRepository {
        private ConcurrentHashMap<String, Employer> employers;

        public EmployerRepository() {
                employers = new ConcurrentHashMap<>();
                employers.put("1a",
                                new Employer("1a", "VNG Corporation", "img/Vng.webp",
                                                "https://vng.com.vn/",
                                                "vngcorporation@vng.com.vn"));
                employers.put("2a",
                                new Employer("2a", "BlueBottle Digital Việt Nam", "img/BlueBottle.webp",
                                                "https://www.bluebottle.vn/",
                                                "recruit@bluebottle.com"));
                employers.put("3a",
                                new Employer("3a", "Viettel Post", "img/Viettel.webp",
                                                "https://viettelpost.com.vn/",
                                                "kinhdoanh@viettelpost.com.vn"));
                employers.put("4a",
                                new Employer("4a", "GEM Corporation", "img/Gem.webp",
                                                "https://gemvietnam.com/",
                                                "contact@gemvietnam.com"));

        }

        public List<Employer> getEmployers() {
                return employers.values().stream().toList();
        }

        public void addEmployer(Employer employer) {
                String uuid = UUID.randomUUID().toString();
                employers.put(uuid, employer);
        }

        public Employer findById(String id) {
                return employers.get(id);
        }

        public void updateEmployer(String id, Employer employer) {
                employers.put(id, employer);
        }

        public void deleteEmployer(String id) {
                employers.remove(id);
        }

}
