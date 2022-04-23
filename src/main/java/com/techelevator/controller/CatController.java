package com.techelevator.controller;

import com.techelevator.dao.CatCardDao;
import com.techelevator.model.CatCard;
import com.techelevator.services.CatFactService;
import com.techelevator.services.CatPicService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/cards")
public class CatController {

    private CatCardDao catCardDao;
    private CatFactService catFactService;
    private CatPicService catPicService;

    public CatController(CatCardDao catCardDao, CatFactService catFactService, CatPicService catPicService) {
        this.catCardDao = catCardDao;
        this.catFactService = catFactService;
        this.catPicService = catPicService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CatCard> listCatCards(){
        return catCardDao.list();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public CatCard getCardById(@PathVariable int id){
        return catCardDao.get(id);
        }

    @RequestMapping(path = "/random", method = RequestMethod.GET)
    public CatCard createCatCard(){
        CatCard catCard = new CatCard();
        catCard.setImgUrl(catPicService.getPic().getFile());
        catCard.setCatFact(catFactService.getFact().getText());
        catCard.setCaption(catFactService.getFact().getText());
        return catCard;
    }

    @RequestMapping(method = RequestMethod.POST)
    public boolean saveCatCard(@RequestBody CatCard catCard){
        return catCardDao.save(catCard);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public boolean updateCard(@RequestBody CatCard catCard, @PathVariable long id) {
        return catCardDao.update(id, catCard);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public boolean removeCard(@PathVariable long id) {
     return catCardDao.delete(id);
    }
}
