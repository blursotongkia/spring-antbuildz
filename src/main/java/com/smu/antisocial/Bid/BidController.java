package com.smu.antisocial.Bid;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "api/v1/bid")
@AllArgsConstructor
public class BidController {
    @Autowired
    private final BidService bidService;

    @GetMapping
	public List<Bid> getBids(){
        return bidService.getBids();
	}

    @GetMapping(path="/successful-bids/{equipmentID}")
	public ArrayList<Double> getSuggestedBids(@PathVariable Integer equipmentID){
        return bidService.getSuggestedBids(equipmentID);
	}

    @PostMapping
    public Bid createBid(@RequestBody Bid bid){
        return bidService.createBid(bid);
    }

    @DeleteMapping(path="{bidID}")
    public void deleteBid(@PathVariable("bidID") Integer bidID){
        bidService.deleteBid(bidID);
    }

    @PutMapping(path="/status/{bidID}")
    public void updateStatus(@PathVariable("bidID") Integer bidID){
        bidService.updateStatus(bidID);
    }

    @GetMapping(path="/request/{requestID}")
	public List<Bid> getLowestBids(@PathVariable("requestID") Integer requestID){
        return bidService.getLowestBids(requestID);
    }

    @GetMapping(path="/generate-invoice/{bidID}")
    public void generateInvoice(@PathVariable("bidID") Integer bidID, @RequestParam String type) throws UnsupportedEncodingException{
        bidService.generatePdfFromHtml(bidID, type);
    }
}
