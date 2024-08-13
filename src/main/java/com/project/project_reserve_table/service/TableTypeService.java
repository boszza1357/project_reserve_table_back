package com.project.project_reserve_table.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.project_reserve_table.entity.TableTypeEntity;
import com.project.project_reserve_table.model.TablesTypeRequsetModel;
import com.project.project_reserve_table.model.TablesTypeResponseModel;
import com.project.project_reserve_table.repository.TableTypeRepository;

import jakarta.transaction.Transactional;

@Service
public class TableTypeService {
	
	@Autowired
	private TableTypeRepository tableTypeRepository;
	
	
public TablesTypeResponseModel getTableTypeById(Integer tableTypeId){
		
		TablesTypeResponseModel response = null;
		
		
		Optional<TableTypeEntity> entity = tableTypeRepository.findById(tableTypeId);
		
		if(entity.isPresent()) {
			
			TableTypeEntity tabletype = entity.get();
			
			response = new TablesTypeResponseModel() ;
			
			response.setTablesTypeId(tabletype.getId());
			response.setTablesTypeName(tabletype.getTablesTypeName());
			response.setTablesTypeDesc(tabletype.getTablesTypeDesc());
			response.setStatus(tabletype.getStatus());
			
		}
		
		return response;
		}

public List<TablesTypeResponseModel> getTableTypeAll(){
	List<TablesTypeResponseModel> response = null;
	
	List<TableTypeEntity> tableImgList = tableTypeRepository.findAll();
	
	if(null != tableImgList) {
		
		response = new ArrayList<>();
		
		for(TableTypeEntity tabletype : tableImgList) {
			
			TablesTypeResponseModel objectResponse = new TablesTypeResponseModel();
			
			objectResponse.setTablesTypeId(tabletype.getId());
			objectResponse.setTablesTypeName(tabletype.getTablesTypeName());
			objectResponse.setTablesTypeDesc(tabletype.getTablesTypeDesc());
			objectResponse.setStatus(tabletype.getStatus());
			
			
			response.add(objectResponse);
		}
	}
	
	return response;
}

public Integer saveTypeTable(TablesTypeRequsetModel tablesTypeRequsetModel)throws IOException{
	
	Integer response = null ;
	
	if(null != tablesTypeRequsetModel) {
		
		TableTypeEntity tableTypeEntity = new TableTypeEntity();
		
		tableTypeEntity.setTablesTypeName(tablesTypeRequsetModel.getTablesTypeName());
		tableTypeEntity.setTablesTypeDesc(tablesTypeRequsetModel.getTablesTypeDesc());
		tableTypeEntity.setStatus("1");
		tableTypeEntity.setCreateBy("Dew");
		tableTypeEntity.setCreateDate(new Date());
		tableTypeEntity.setUpdateBy("Dew");
		tableTypeEntity.setUpdateDate(new Date());
		
		tableTypeEntity = tableTypeRepository.save(tableTypeEntity);
		response = tableTypeEntity.getId();
		
	}
	
	return response ;
}

	@Transactional
	public Integer update(TablesTypeRequsetModel tablesTypeRequsetModel,Integer tableTypeId) throws IOException{
		
		Integer response = null ;
		
		Optional<TableTypeEntity> tableTypeEntity =  tableTypeRepository.findById(tableTypeId);
		
		if(tableTypeEntity.isPresent()) {
			
			TableTypeEntity tableType = tableTypeEntity.get();
			
			tableType.setTablesTypeName(null != tablesTypeRequsetModel.getTablesTypeName() ? tablesTypeRequsetModel.getTablesTypeName() : tableType.getTablesTypeName() );
			tableType.setTablesTypeDesc(null !=  tablesTypeRequsetModel.getTablesTypeDesc() ? tablesTypeRequsetModel.getTablesTypeDesc() : tableType.getTablesTypeDesc());
			tableType.setStatus(null != tablesTypeRequsetModel.getStatus() ? tablesTypeRequsetModel.getStatus() : tableType.getStatus());
			tableType.setUpdateBy("Dew");
			tableType.setUpdateDate(new Date());
			
			tableType = tableTypeRepository.save(tableType);
			
			response = tableType.getId();
		}
		
		
		return response ; 
	}
	
	@Transactional
	public Integer delete(Integer tableTypeId) throws IOException{
		
		Integer response = null ;
	
		if(null != tableTypeId) {
			
			tableTypeRepository.deleteBytableTypeId(tableTypeId);
			response = tableTypeId;
			
		}
		
		return response ;
	}

}
