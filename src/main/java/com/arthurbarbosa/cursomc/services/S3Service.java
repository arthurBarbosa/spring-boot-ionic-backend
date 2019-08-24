package com.arthurbarbosa.cursomc.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class S3Service implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 s3cliente;

    @Value("${s3.bucket}")
    private String bucketName;

    //    public void uploadFile(String localFilePath){
//        try {
//            File file = new File(localFilePath);
//            LOG.info("iniciando o upload");
//            s3cliente.putObject(bucketName, "nomeDoArquivo.jpg", file);
//            LOG.info("Upload finalizado");
//        }catch (AmazonServiceException e){
//            LOG.info("AmazonServiceException" +e.getMessage());
//            LOG.info("Status code:" +e.getErrorCode());
//        }catch(AmazonClientException erroCliente){
//            LOG.info("AmazonClientException" + erroCliente.getMessage());
//        }
//    }
    public URI uploadFile(MultipartFile multipartFile) {
        try {
            //File file = new File(localFilePath);
            String nomeArquivo = multipartFile.getName();
            InputStream iStream = multipartFile.getInputStream();
            String tipoDoConteudo = multipartFile.getContentType();
            return uploadFile(iStream, nomeArquivo, tipoDoConteudo);
        } catch (IOException erroIO) {
            throw new RuntimeException("Erro de IO " + erroIO.getMessage());
        }
    }

    public URI uploadFile(InputStream inputStream, String fileName, String tipo) {
        try {
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType(tipo);
            LOG.info("Iniciando upload");
            // s3cliente.putObject(bucketName, "NomeDoArquivo.jpg", file);
            s3cliente.putObject(bucketName, fileName, inputStream, meta);
            LOG.info("Upload finalizado");

            return s3cliente.getUrl(bucketName, fileName).toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Erro ao tentar converter URL em URI");
        }
    }
}
