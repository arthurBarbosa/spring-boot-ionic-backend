package com.arthurbarbosa.cursomc.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.Serializable;

public class S3Service implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 s3cliente;

    @Value("${s3.bucket}")
    private String bucketName;

    private void uploadFile(String localFilePath){
        try {
            File file = new File(localFilePath);
            LOG.info("iniciando o upload");
            s3cliente.putObject(bucketName, "nomeDoArquivo.jpg", file);
            LOG.info("Upload finalizado");
        }catch (AmazonServiceException e){
            LOG.info("AmazonServiceException" +e.getMessage());
            LOG.info("Status code:" +e.getErrorCode());
        }catch(AmazonClientException erroCliente){
            LOG.info("AmazonClientException" + erroCliente.getMessage());
        }
    }
}
