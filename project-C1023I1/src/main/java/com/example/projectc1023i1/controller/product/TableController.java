package com.example.projectc1023i1.controller.product;


import com.example.projectc1023i1.model.product.Table;
import com.example.projectc1023i1.service.product.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/table")
@RequiredArgsConstructor
public class TableController {
    @Autowired
    private TableService tableService;

    @GetMapping("/get_table")
    public ResponseEntity<List<Table>> findAllTable(){
        List<Table> tableList = tableService.findAllTable();
        if(tableList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(tableList,HttpStatus.OK);
        }
    }

    @GetMapping("/getTableByCode")
    public ResponseEntity<Table> findTableByCode(@PathVariable ("code" )String code){
        Table table=tableService.findTableByCode(code);
        if (table== null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(table, HttpStatus.OK);
        }
    }
    @GetMapping("/getAllTableByStatus")
    public ResponseEntity<List<Table>> findAllTableByStatus(@PathVariable ("status" )boolean status){
        List<Table> tableList = tableService.findTableByStatus(status);
        if(tableList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(tableList,HttpStatus.OK);
        }
    }

      @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> delete(@PathVariable("id") int id) {
    boolean isDeleted = tableService.deleteTableById(id);

    if (isDeleted) {
        return ResponseEntity.ok("Table with ID " + id + " has been deleted successfully.");
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Table with ID " + id + " not found.");
    }
}

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTable(@PathVariable("id") Long tableId, @RequestParam boolean newStatus) {
        boolean isUpdated = tableService.updateTableById(tableId, newStatus);

        if (isUpdated) {
            return ResponseEntity.ok("Table with ID " + tableId + " has been updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Table with ID " + tableId + " not found.");
        }
    }
    @PostMapping("/create")
    public ResponseEntity<String> createTable() {
        try {
            tableService.createTable();
            return ResponseEntity.ok("Table has been created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while creating the table: " + e.getMessage());
        }
    }




}
