package com.jr.valid.frame.model;

import jakarta.validation.Payload;

public interface ValidationLevel {
    interface MUST extends Payload, ValidationLevel {
    }

    interface WARN extends Payload, ValidationLevel {
    }

    interface SILENT extends Payload, ValidationLevel {
    }
}
