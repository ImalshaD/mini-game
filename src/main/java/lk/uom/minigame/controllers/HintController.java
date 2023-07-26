package lk.uom.minigame.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "hint")
@CrossOrigin
public class HintController {
//    @GetMapping("/getHint")
//    public HintDto getHint(HttpServletRequest request){
//        Memo memo = Memo.getInstance();
//        CaseMemo caseMemo = memo.getCaseMemo(request);
//        System.out.println();
//        return new HintDto(caseMemo.getCaseNumber(), caseMemo.getHintNumber());
//    }
//    @GetMapping("/nextHint")
//    public HintDto getNextHint(HttpServletRequest request){
//        Memo memo = Memo.getInstance();
//        CaseMemo caseMemo = memo.getCaseMemo(request);
//        caseMemo.nextHint();
//        return new HintDto(caseMemo.getCaseNumber(), caseMemo.getHintNumber());
//    }
}
