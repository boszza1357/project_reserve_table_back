package com.project.project_reserve_table.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.project.project_reserve_table.entity.TableEntity;
import com.project.project_reserve_table.entity.TableImgEntity;
import com.project.project_reserve_table.entity.TableTypeEntity;
import com.project.project_reserve_table.model.TablesImgResponseModel;
import com.project.project_reserve_table.model.TablesRequsetModel;
import com.project.project_reserve_table.model.TablesResponseModel;
import com.project.project_reserve_table.model.TablesTypeRequsetModel;
import com.project.project_reserve_table.model.TablesTypeResponseModel;
import com.project.project_reserve_table.repository.TableImgRepository;
import com.project.project_reserve_table.repository.TableRepository;
import com.project.project_reserve_table.repository.TableTypeRepository;
import com.project.project_reserve_table.utils.Constants;
import com.project.project_reserve_table.utils.ImgUtils;

import jakarta.transaction.Transactional;



@Service
public class TableService {
	
	


	@Autowired
	private TableRepository  tableRepository;
	
	@Autowired
	private TableImgRepository tableimgRepository;
	
	@Autowired
	private TableTypeRepository tabletypeRepository;
	
	@Autowired
	private GenPdfService genPdfService;
	
	
	public TablesResponseModel getById(Integer tableId) {
		
		TablesResponseModel response = null;
		
		Optional<TableEntity> entity = tableRepository.findById(tableId);
		
		if(entity.isPresent()) {
			
			TableEntity table = entity.get();
			
			response = new TablesResponseModel();
			
			response.setTablesId(table.getId());
			response.setTablesTypeId(table.getTablesTypeId());
			response.setTableName(table.getTableName());
			response.setTableNumber(table.getTableNumber());
			response.setTableDesc(table.getTableDesc());
			response.setCapacity(table.getCapacity());
			response.setStatus(table.getStatus());
			response.setPrice(table.getPrice());
			response.setNots(table.getNots());
			
		}
		
		return response;
	}
	
	public List<TablesResponseModel> getAll() {
		
		List<TablesResponseModel> response = null;
		
		List<TableEntity> entitys = tableRepository.findAll();
		
		if(null != entitys) {
			response = new ArrayList<>();
			for(TableEntity table : entitys) {
				
				TablesResponseModel object = new TablesResponseModel();
				
				object.setTablesId(table.getId());
				object.setTablesTypeId(table.getTablesTypeId());
				object.setTableName(table.getTableName());
				object.setTableNumber(table.getTableNumber());
				object.setTableDesc(table.getTableDesc());
				object.setCapacity(table.getCapacity());
				object.setStatus(table.getStatus());
				object.setPrice(table.getPrice());
				object.setNots(table.getNots());
				
				
				
				response.add(object);
			}

		}

		return response;
		
	}
	@Transactional
	public Integer save(TablesRequsetModel tableRequest) throws IOException {
		
		Integer response = null;
		
		if(null != tableRequest) {
			
			TableEntity	tableEntity = new TableEntity();
			
			tableEntity.setTableName(tableRequest.getTableName());
			tableEntity.setTableNumber(tableRequest.getTableNumber());
			tableEntity.setTableDesc(tableRequest.getTableDesc());
			tableEntity.setTablesTypeId(tableRequest.getTablesTypeId());
			tableEntity.setCapacity(tableRequest.getCapacity());
			tableEntity.setPrice(tableRequest.getPrice());
			tableEntity.setNots(tableRequest.getNots());
			tableEntity.setStatus("1");
			tableEntity.setCreateBy("Dew");
			tableEntity.setCreateDate(new Date());
			tableEntity.setUpdateBy("Dew Update");
			tableEntity.setUpdateDate(new Date());
			
			tableEntity = tableRepository.save(tableEntity);
			response = tableEntity.getId();
			
		}
		
		
		return response;
		
	}
	@Transactional
	public Integer update(TablesRequsetModel tableRequest, Integer tableId) throws IOException {
		Integer response = null;
		
		Optional<TableEntity> tableEntity = tableRepository.findById(tableId);
		
		if(tableEntity.isPresent()) {
			
			TableEntity table = tableEntity.get();
			
			table.setTableName(null != tableRequest.getTableName() ? tableRequest.getTableName() : table.getTableName());
			table.setTableNumber(null != tableRequest.getTableNumber() ? tableRequest.getTableNumber() : table.getTableNumber());
			table.setTableDesc(null != tableRequest.getTableDesc() ? tableRequest.getTableDesc() : table.getTableDesc());
			table.setStatus(null != tableRequest.getStatus() ? tableRequest.getStatus() : table.getStatus());
			table.setPrice(null != tableRequest.getPrice() ? tableRequest.getPrice() : table.getPrice());
			table.setTablesTypeId(null != tableRequest.getTablesTypeId() ? tableRequest.getTablesTypeId() : table.getTablesTypeId());
			table.setCapacity(null != tableRequest.getCapacity() ? tableRequest.getCapacity() : table.getCapacity());
			table.setNots(null != tableRequest.getCapacity() ? tableRequest.getNots() : table.getNots());
			table.setUpdateBy("Joey Update New");
			table.setUpdateDate(new Date());
			
			table = tableRepository.save(table);
			
			response = table.getId();
			
		}
		
		return response;
	}
	@Transactional
	public Integer delete(Integer tableId) throws IOException {
		
		Integer response = null;
		
		if(null != tableId) {
			tableRepository.deleteByTableId(tableId);
			this.removeImgAndDeleteFileImgByProductId(tableId);
			response = tableId;
		}
		return response;
	}
	

	
	public List<TablesImgResponseModel> getProductImgByProductId(Integer tableId){
		List<TablesImgResponseModel> response = null;
		
		List<TableImgEntity> tableImgList = tableimgRepository.findByTableId(tableId);
		
		if(null != tableImgList) {
			response = new ArrayList<>();
			for(TableImgEntity tableImg : tableImgList) {
				
				TablesImgResponseModel objectResponse = new TablesImgResponseModel();
				
				objectResponse.setTableImgId(tableImg.getId());
				objectResponse.setTableId(tableImg.getTableId());
				objectResponse.setTableImgPath(tableImg.getTableImgPath());
				objectResponse.setTableImgName(tableImg.getTableImgName());
				objectResponse.setTableImgData(tableImg.getTablesImgData());
				objectResponse.setStatus(tableImg.getStatus());
				
				response.add(objectResponse);
			}
		}
		
		return response;
	}
	
	public byte[] getImageByte(String fileName) throws IOException, DataFormatException {
		TableImgEntity taleImg =tableimgRepository.findByTableName(fileName);
		
		if(null != taleImg) {
			return ImgUtils.decompressImage(taleImg.getTablesImgData());
		}

        return null;
	}
	
	@Transactional
	public Integer saveImage(MultipartFile file, Integer tableId) throws IOException {
		Integer response = null;
		if(null != file && null != tableId) {
			TableImgEntity tableImg = new TableImgEntity();
			String preFixNameFile = ImgUtils.genaratePrefixFile();
			String genarateFileName = ImgUtils.genarateFileName() +ImgUtils.subStrFileName(file.getOriginalFilename());
			String fileName = ImgUtils.concatStr(preFixNameFile, genarateFileName);
			tableImg.setTableImgName(fileName);
			tableImg.setTableImgPath(ImgUtils.getPathInput());
			tableImg.setTableId(tableId);
			tableImg.setTablesImgData(ImgUtils.compressImage(file.getBytes()));
			tableImg.setStatus("1");
			tableImg.setCreateBy("Dew");
			tableImg.setCreateDate(new Date());
			tableImg.setUpdateBy("Dew Update");
			tableImg.setUpdateDate(new Date());
			tableImg = tableimgRepository.save(tableImg);
			response = tableImg.getId();
			
//			ImgUtils.saveFile(file, fileName, Constants.PATH_TYPE_INPUT);
		}
		return response;
	}
	
	
	@Transactional
	public void deleteImgSever(String fileName) throws IOException {
		if(null != fileName) {
			ImgUtils.deleteFile(fileName, Constants.PATH_TYPE_INPUT);
		}
		
	}
	
	
	
	
	
	@Transactional
	public void removeImgAndDeleteFileImgByProductId(Integer tableId) throws IOException {
		List<TableImgEntity> tableImgList = tableimgRepository.findByTableId(tableId);
		tableimgRepository.deleteAll(tableImgList);
	}
	
	@Transactional
	public void deleteImgByFileName(String fileName) throws IOException {
		TableImgEntity tableImgList = tableimgRepository.findByTableName(fileName);
		tableimgRepository.delete(tableImgList);
	}
}
