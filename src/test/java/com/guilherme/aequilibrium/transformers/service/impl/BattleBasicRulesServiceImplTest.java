package com.guilherme.aequilibrium.transformers.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.guilherme.aequilibrium.transformers.model.TransformerEntity;

@RunWith(SpringJUnit4ClassRunner.class)
public class BattleBasicRulesServiceImplTest {
    
 
    @InjectMocks
    private BattleBasicRulesServiceImpl service;
    
    @Test
    public void test() {
	//setup
	
	List<TransformerEntity> transformers = new ArrayList<>();
	
	service.applyRules(transformers);
	
    }
}
