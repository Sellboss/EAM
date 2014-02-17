package se.sellboss.eam.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import se.sellboss.eam.domain.AssetType;

public interface AssetTypeRepository extends
		PagingAndSortingRepository<AssetType, String> {

}
