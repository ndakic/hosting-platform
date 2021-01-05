package uns.ac.rs.hostplatserver.rest;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uns.ac.rs.hostplatserver.resource.LabelResource;
import uns.ac.rs.hostplatserver.service.LabelService;

@RestController
@RequestMapping("/label")
@RequiredArgsConstructor
public class LabelController {

    @NonNull
    private final LabelService labelService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody LabelResource labelResource){
        return ResponseEntity.ok(labelService.create(labelResource));
    }

    @RequestMapping(value = "{labelId}", method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable String labelId){
        return ResponseEntity.ok(labelService.get(labelId));
    }

    @RequestMapping(value = "/active", method = RequestMethod.GET)
    public ResponseEntity<?> getAllActive(){
        return ResponseEntity.ok(labelService.getAllActive());
    }

    @RequestMapping(value = "/{labelId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable String labelId){
        return ResponseEntity.ok(labelService.delete(labelId));
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody LabelResource labelResource){
        return ResponseEntity.ok(labelService.update(labelResource));
    }
}
