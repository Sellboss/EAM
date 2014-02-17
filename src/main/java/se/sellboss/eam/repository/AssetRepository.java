package se.sellboss.eam.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import se.sellboss.eam.domain.Asset;

public interface AssetRepository extends
		PagingAndSortingRepository<Asset, String>, AssetRepostitoryCustom {

}
