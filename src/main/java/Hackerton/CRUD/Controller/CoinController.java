package Hackerton.CRUD.Controller;

import Hackerton.CRUD.Service.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coins")
public class CoinController {

    private final CoinService coinService;

    @Autowired
    public CoinController(CoinService coinService) {
        this.coinService = coinService;
    }

    @GetMapping("/balance/{memberId}")
    public ResponseEntity<Integer> getCoinBalance(@PathVariable Long memberId) {
        int balance = coinService.getCoinBalance(memberId);
        return ResponseEntity.ok(balance);
    }
}
