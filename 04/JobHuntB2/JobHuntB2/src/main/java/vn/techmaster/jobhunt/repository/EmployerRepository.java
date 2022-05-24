package vn.techmaster.jobhunt.repository;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import vn.techmaster.jobhunt.model.Employer;

@Repository
public class EmployerRepository {
        private ConcurrentHashMap<String, Employer> employers;

        public EmployerRepository() {
                employers = new ConcurrentHashMap<>();
                employers.put("1a", new Employer("1a", "VNG Corporation", "/resources/vng.webp", "https://vng.com.vn/",
                                "vngcorporation@vng.com.vn"));
                employers.put("2a",
                                new Employer("2a", "BlueBottle Digital Việt Nam", "/resources/BlueBottle.webp",
                                                "https://www.bluebottle.vn/",
                                                "recruit@castis.com"));
                employers.put("3a",
                                new Employer("3a", "Viettel Post", "/resources/Viettel.webp",
                                                "https://viettelpost.com.vn/",
                                                "kinhdoanh@viettelpost.com.vn"));
                employers.put("4a",
                                new Employer("4a", "MB Bank", "/resources/mbbank.webp", "https://www.mbbank.com.vn/",
                                                "mb247@mbbank.com.vn"));

        }

        public ConcurrentHashMap<String, Employer> getEmployers() {
                return employers;
        }
}
