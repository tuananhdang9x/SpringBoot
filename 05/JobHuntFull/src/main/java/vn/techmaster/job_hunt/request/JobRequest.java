package vn.techmaster.job_hunt.request;

import javax.validation.constraints.NotBlank;

import vn.techmaster.job_hunt.model.City;

public record JobRequest(
        String id,
        String emp_id,

        @NotBlank(message = "Title cannot null") String title,

        @NotBlank(message = "Description cannot null") String description,
        City city) {
}
