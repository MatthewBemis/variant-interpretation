package org.broadinstitute.variantinterpretation;

import java.time.Instant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/api/hello")
  public HelloResponse hello() {
    return new HelloResponse(Instant.now().toString());
  }

  public record HelloResponse(String timestamp) {}
}
