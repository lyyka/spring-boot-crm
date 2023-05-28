package com.example.lr20190024.deals.services.impl;

import com.example.lr20190024.clients.entities.Client;
import com.example.lr20190024.clients.repositories.ClientsRepository;
import com.example.lr20190024.common.exception.ResourceNotFoundException;
import com.example.lr20190024.deals.entities.Deal;
import com.example.lr20190024.deals.repositories.DealsRepository;
import com.example.lr20190024.deals.requests.DealStageUpdateRequest;
import com.example.lr20190024.deals.requests.DealStoreRequest;
import com.example.lr20190024.deals.requests.DealUpdateRequest;
import com.example.lr20190024.deals.responses.DealResponse;
import com.example.lr20190024.deals.services.IDealService;
import com.example.lr20190024.stages.entities.Stage;
import com.example.lr20190024.stages.repositories.StagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DealService implements IDealService {
    private final DealsRepository dealsRepository;
    private final ClientsRepository clientsRepository;
    private final StagesRepository stagesRepository;

    @Override
    public List<DealResponse> getForClient(Long clientId) {
        return this.dealsRepository.findByClientId(clientId).stream().map(DealResponse::fromEntity).toList();
    }

    @Override
    public DealResponse get(Long id) {
        return DealResponse.fromEntity(
                this.dealsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Deal not found"))
        );
    }

    @Override
    public DealResponse store(DealStoreRequest request) {
        Stage stage = this.stagesRepository.findById(request.getStageId()).orElseThrow(() -> new ResourceNotFoundException("Stage not found"));
        Client client = this.clientsRepository.findById(request.getClientId()).orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        return DealResponse.fromEntity(
                this.dealsRepository.save(
                        new Deal(
                                request.getName(),
                                request.getDealStatus(),
                                client,
                                stage
                        )
                )
        );
    }

    @Override
    public DealResponse update(Long id, DealUpdateRequest request) {
        Deal deal = this.dealsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Deal not found"));
        Stage stage = this.stagesRepository.findById(request.getStageId()).orElseThrow(() -> new ResourceNotFoundException("Stage not found"));
        deal.setName(request.getName());
        deal.setDealStatus(request.getDealStatus());
        deal.setStage(stage);
        return DealResponse.fromEntity(this.dealsRepository.save(deal));
    }

    @Override
    public DealResponse updateStage(Long id, DealStageUpdateRequest request) {
        Deal deal = this.dealsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Deal not found"));
        Stage stage = this.stagesRepository.findById(request.getStageId()).orElseThrow(() -> new ResourceNotFoundException("Stage not found"));
        deal.setStage(stage);
        return DealResponse.fromEntity(this.dealsRepository.save(deal));
    }

    @Override
    public void delete(Long id) {
        this.dealsRepository.deleteById(id);
    }
}
