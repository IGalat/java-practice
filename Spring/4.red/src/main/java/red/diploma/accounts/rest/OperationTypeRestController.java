package red.diploma.accounts.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import red.diploma.accounts.model.OperationType;
import red.diploma.accounts.service.OperationTypeService;

import java.util.List;

@RestController
@RequestMapping("op/type")
public class OperationTypeRestController {
    private final OperationTypeService operationTypeService;

    @Autowired
    public OperationTypeRestController(OperationTypeService operationTypeService) {
        this.operationTypeService = operationTypeService;
    }


    @GetMapping("all")
    public List<OperationType> findAll() {
        return operationTypeService.findAll();
    }

    @GetMapping("id/{id}")
    public OperationType getOne(@PathVariable Long id) {
        return operationTypeService.getOne(id);
    }

    @GetMapping("{name}")
    public OperationType getByName(@PathVariable String name) {
        return operationTypeService.getByName(name);
    }

    @PutMapping
    public void update(@RequestBody OperationType operationType) {
        operationTypeService.update(operationType);
    }
}
