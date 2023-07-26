package lk.uom.minigame.controllers;

import lk.uom.minigame.DTO.AttemptArrayDto;
import lk.uom.minigame.Entity.Attempt;
import lk.uom.minigame.Utility.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value="admin")
@CrossOrigin
public class AdminController{
    private Memo memo;

    public AdminController() {
        this.memo = Memo.getInstance();
    }

    @PutMapping("/restart")
    public void restartGame(){
        memo.cleanDict();
    }
    @GetMapping("/order")
    public AttemptArrayDto getOrder(){
        return new AttemptArrayDto(memo.getOrder());
    }
}
