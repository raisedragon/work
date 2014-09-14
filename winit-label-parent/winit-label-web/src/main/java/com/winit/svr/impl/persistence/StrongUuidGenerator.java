package com.winit.svr.impl.persistence;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import com.winit.svr.impl.cfg.IdGenerator;

/**
 * {@link IdGenerator} implementation based on the current time and the ethernet
 * address of the machine it is running on.
 * 
 * @author Daniel Meyer
 */
public class StrongUuidGenerator implements IdGenerator {

  // different ProcessEngines on the same classloader share one generator.
  protected static TimeBasedGenerator timeBasedGenerator;

  public StrongUuidGenerator() {
    ensureGeneratorInitialized();
  }

  protected void ensureGeneratorInitialized() {
    if (timeBasedGenerator == null) {
      synchronized (StrongUuidGenerator.class) {
        if (timeBasedGenerator == null) {
          timeBasedGenerator = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
        }
      }
    }
  }

  public String getNextId() {
    return timeBasedGenerator.generate().toString();
  }

}
