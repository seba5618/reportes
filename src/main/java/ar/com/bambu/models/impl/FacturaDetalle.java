package ar.com.bambu.models.impl;

import java.io.Serializable;

public class FacturaDetalle implements Serializable {
    public Double f2_DESCONT;
    private String f2_XOBS;
    private Double d2_PRUNIT;
    private Double c6_DESCONT=4.6;
    private String REMITOS;
    private Double DESCPV;
    private Double VALMERC;
    private String DOCESPECIE;
    private String DOCSERIE="X";
    private String SERIE;
    private String CAEE;
    private String EMCAEE;
    private String NUMDOC;
    private String NUMERODOCORI;
    private String CUITCLI;
    private String IIBB;
    private Double retencion_IVA_21;
    private Double retencion_IVA_105;
    private String FECHADIGIT;
    private Double TOTALDOC;
    private Double TOTIVA;
    private String DIRECC;
    private String LOCALIDAD;
    private String NOMBRECLI;
    private String CONTACTOCLI;
    private String TIENDA;
    private String CLIENTE;
    private String DESCCONDPAGO;
    private String copy;
    private Integer sequence;
    private String TRANSPORTE;
    private String DIRTRANS;
    private String TELTRANS;
    private String a1_TIPO;
    private String TIPODOC;
    private String COD_TIPODOC;
    private String d2_ITEM;
    private String d2_COD;
    private Double d2_QUANT;
    private Double d2_PRCVEN;
    private Double d2_TOTAL;
    private Double IVA21;
    private Double IVA105;
    private String REMITO;
    private String SERREM;
    private String b1_DESC="Tet";
    private String b1_SEGUM;
    private String b1_UM;
    private String letras_ESP;
    private String CODBARRAS;

    public void setTipoComprobante(int tipoEvento) {
        if (tipoEvento == 16) {
            this.DOCSERIE = "A";
        } else if (tipoEvento == 17) {
            this.DOCSERIE = "B";
        } else {
            this.DOCSERIE = "X";
        }
    }

    public void setNumeroYTipoComprobante(int caja, long ticket){
        String.format("%04d%08d",caja, ticket);
        this.NUMDOC= String.format("%04d-%08d",caja, ticket);
        this.DOCESPECIE = "NF";
    }

    public Double getF2_DESCONT() {
        return f2_DESCONT;
    }

    public void setF2_DESCONT(Double f2_DESCONT) {
        this.f2_DESCONT = f2_DESCONT;
    }

    public String getF2_XOBS() {
        return f2_XOBS;
    }

    public void setF2_XOBS(String f2_XOBS) {
        this.f2_XOBS = f2_XOBS;
    }

    public Double getD2_PRUNIT() {
        return d2_PRUNIT;
    }

    public void setD2_PRUNIT(Double d2_PRUNIT) {
        this.d2_PRUNIT = d2_PRUNIT;
    }

    public Double getC6_DESCONT() {
        return c6_DESCONT;
    }

    public void setC6_DESCONT(Double c6_DESCONT) {
        this.c6_DESCONT = c6_DESCONT;
    }

    public String getREMITOS() {
        return REMITOS;
    }

    public void setREMITOS(String REMITOS) {
        this.REMITOS = REMITOS;
    }

    public Double getDESCPV() {
        return DESCPV;
    }

    public void setDESCPV(Double DESCPV) {
        this.DESCPV = DESCPV;
    }

    public Double getVALMERC() {
        return VALMERC;
    }

    public void setVALMERC(Double VALMERC) {
        this.VALMERC = VALMERC;
    }

    public String getDOCESPECIE() {
        return DOCESPECIE;
    }

    public void setDOCESPECIE(String DOCESPECIE) {
        this.DOCESPECIE = DOCESPECIE;
    }

    public String getDOCSERIE() {
        return DOCSERIE;
    }

    public void setDOCSERIE(String DOCSERIE) {
        this.DOCSERIE = DOCSERIE;
    }

    public String getSERIE() {
        return SERIE;
    }

    public void setSERIE(String SERIE) {
        this.SERIE = SERIE;
    }

    public String getCAEE() {
        return CAEE;
    }

    public void setCAEE(String CAEE) {
        this.CAEE = CAEE;
    }

    public String getEMCAEE() {
        return EMCAEE;
    }

    public void setEMCAEE(String EMCAEE) {
        this.EMCAEE = EMCAEE;
    }

    public String getNUMDOC() {
        return NUMDOC;
    }

    public void setNUMDOC(String NUMDOC) {
        this.NUMDOC = NUMDOC;
    }

    public String getNUMERODOCORI() {
        return NUMERODOCORI;
    }

    public void setNUMERODOCORI(String NUMERODOCORI) {
        this.NUMERODOCORI = NUMERODOCORI;
    }

    public String getCUITCLI() {
        return CUITCLI;
    }

    public void setCUITCLI(String CUITCLI) {
        this.CUITCLI = CUITCLI;
    }

    public String getIIBB() {
        return IIBB;
    }

    public void setIIBB(String IIBB) {
        this.IIBB = IIBB;
    }

    public Double getRetencion_IVA_21() {
        return retencion_IVA_21;
    }

    public void setRetencion_IVA_21(Double retencion_IVA_21) {
        this.retencion_IVA_21 = retencion_IVA_21;
    }

    public Double getRetencion_IVA_105() {
        return retencion_IVA_105;
    }

    public void setRetencion_IVA_105(Double retencion_IVA_105) {
        this.retencion_IVA_105 = retencion_IVA_105;
    }

    public String getFECHADIGIT() {
        return FECHADIGIT;
    }

    public void setFECHADIGIT(String FECHADIGIT) {
        this.FECHADIGIT = FECHADIGIT;
    }

    public Double getTOTALDOC() {
        return TOTALDOC;
    }

    public void setTOTALDOC(Double TOTALDOC) {
        this.TOTALDOC = TOTALDOC;
    }

    public Double getTOTIVA() {
        return TOTIVA;
    }

    public void setTOTIVA(Double TOTIVA) {
        this.TOTIVA = TOTIVA;
    }

    public String getDIRECC() {
        return DIRECC;
    }

    public void setDIRECC(String DIRECC) {
        this.DIRECC = DIRECC;
    }

    public String getLOCALIDAD() {
        return LOCALIDAD;
    }

    public void setLOCALIDAD(String LOCALIDAD) {
        this.LOCALIDAD = LOCALIDAD;
    }

    public String getNOMBRECLI() {
        return NOMBRECLI;
    }

    public void setNOMBRECLI(String NOMBRECLI) {
        this.NOMBRECLI = NOMBRECLI;
    }

    public String getCONTACTOCLI() {
        return CONTACTOCLI;
    }

    public void setCONTACTOCLI(String CONTACTOCLI) {
        this.CONTACTOCLI = CONTACTOCLI;
    }

    public String getTIENDA() {
        return TIENDA;
    }

    public void setTIENDA(String TIENDA) {
        this.TIENDA = TIENDA;
    }

    public String getCLIENTE() {
        return CLIENTE;
    }

    public void setCLIENTE(String CLIENTE) {
        this.CLIENTE = CLIENTE;
    }

    public String getDESCCONDPAGO() {
        return DESCCONDPAGO;
    }

    public void setDESCCONDPAGO(String DESCCONDPAGO) {
        this.DESCCONDPAGO = DESCCONDPAGO;
    }

    public String getCopy() {
        return copy;
    }

    public void setCopy(String copy) {
        this.copy = copy;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getTRANSPORTE() {
        return TRANSPORTE;
    }

    public void setTRANSPORTE(String TRANSPORTE) {
        this.TRANSPORTE = TRANSPORTE;
    }

    public String getDIRTRANS() {
        return DIRTRANS;
    }

    public void setDIRTRANS(String DIRTRANS) {
        this.DIRTRANS = DIRTRANS;
    }

    public String getTELTRANS() {
        return TELTRANS;
    }

    public void setTELTRANS(String TELTRANS) {
        this.TELTRANS = TELTRANS;
    }

    public String getA1_TIPO() {
        return a1_TIPO;
    }

    public void setA1_TIPO(String a1_TIPO) {
        this.a1_TIPO = a1_TIPO;
    }

    public String getTIPODOC() {
        return TIPODOC;
    }

    public void setTIPODOC(String TIPODOC) {
        this.TIPODOC = TIPODOC;
    }

    public String getCOD_TIPODOC() {
        return COD_TIPODOC;
    }

    public void setCOD_TIPODOC(String COD_TIPODOC) {
        this.COD_TIPODOC = COD_TIPODOC;
    }

    public String getD2_ITEM() {
        return d2_ITEM;
    }

    public void setD2_ITEM(String d2_ITEM) {
        this.d2_ITEM = d2_ITEM;
    }

    public String getD2_COD() {
        return d2_COD;
    }

    public void setD2_COD(String d2_COD) {
        this.d2_COD = d2_COD;
    }

    public Double getD2_QUANT() {
        return d2_QUANT;
    }

    public void setD2_QUANT(Double d2_QUANT) {
        this.d2_QUANT = d2_QUANT;
    }

    public Double getD2_PRCVEN() {
        return d2_PRCVEN;
    }

    public void setD2_PRCVEN(Double d2_PRCVEN) {
        this.d2_PRCVEN = d2_PRCVEN;
    }

    public Double getD2_TOTAL() {
        return d2_TOTAL;
    }

    public void setD2_TOTAL(Double d2_TOTAL) {
        this.d2_TOTAL = d2_TOTAL;
    }

    public Double getIVA21() {
        return IVA21;
    }

    public void setIVA21(Double IVA21) {
        this.IVA21 = IVA21;
    }

    public Double getIVA105() {
        return IVA105;
    }

    public void setIVA105(Double IVA105) {
        this.IVA105 = IVA105;
    }

    public String getREMITO() {
        return REMITO;
    }

    public void setREMITO(String REMITO) {
        this.REMITO = REMITO;
    }

    public String getSERREM() {
        return SERREM;
    }

    public void setSERREM(String SERREM) {
        this.SERREM = SERREM;
    }

    public String getB1_DESC() {
        return b1_DESC;
    }

    public void setB1_DESC(String b1_DESC) {
        this.b1_DESC = b1_DESC;
    }

    public String getB1_SEGUM() {
        return b1_SEGUM;
    }

    public void setB1_SEGUM(String b1_SEGUM) {
        this.b1_SEGUM = b1_SEGUM;
    }

    public String getB1_UM() {
        return b1_UM;
    }

    public void setB1_UM(String b1_UM) {
        this.b1_UM = b1_UM;
    }

    public String getLetras_ESP() {
        return letras_ESP;
    }

    public void setLetras_ESP(String letras_ESP) {
        this.letras_ESP = letras_ESP;
    }

    public String getCODBARRAS() {
        return CODBARRAS;
    }

    public void setCODBARRAS(String CODBARRAS) {
        this.CODBARRAS = CODBARRAS;
    }

    @Override
    public String toString() {
        return "FacturaDetalle{" +
                "f2_DESCONT=" + f2_DESCONT +
                ", f2_XOBS='" + f2_XOBS + '\'' +
                ", d2_PRUNIT=" + d2_PRUNIT +
                ", c6_DESCONT=" + c6_DESCONT +
                ", REMITOS='" + REMITOS + '\'' +
                ", DESCPV=" + DESCPV +
                ", VALMERC=" + VALMERC +
                ", DOCESPECIE='" + DOCESPECIE + '\'' +
                ", DOCSERIE='" + DOCSERIE + '\'' +
                ", SERIE='" + SERIE + '\'' +
                ", CAEE='" + CAEE + '\'' +
                ", EMCAEE='" + EMCAEE + '\'' +
                ", NUMDOC='" + NUMDOC + '\'' +
                ", NUMERODOCORI='" + NUMERODOCORI + '\'' +
                ", CUITCLI='" + CUITCLI + '\'' +
                ", IIBB='" + IIBB + '\'' +
                ", retencion_IVA_21=" + retencion_IVA_21 +
                ", retencion_IVA_105=" + retencion_IVA_105 +
                ", FECHADIGIT='" + FECHADIGIT + '\'' +
                ", TOTALDOC=" + TOTALDOC +
                ", TOTIVA=" + TOTIVA +
                ", DIRECC='" + DIRECC + '\'' +
                ", LOCALIDAD='" + LOCALIDAD + '\'' +
                ", NOMBRECLI='" + NOMBRECLI + '\'' +
                ", CONTACTOCLI='" + CONTACTOCLI + '\'' +
                ", TIENDA='" + TIENDA + '\'' +
                ", CLIENTE='" + CLIENTE + '\'' +
                ", DESCCONDPAGO='" + DESCCONDPAGO + '\'' +
                ", copy='" + copy + '\'' +
                ", sequence=" + sequence +
                ", TRANSPORTE='" + TRANSPORTE + '\'' +
                ", DIRTRANS='" + DIRTRANS + '\'' +
                ", TELTRANS='" + TELTRANS + '\'' +
                ", a1_TIPO='" + a1_TIPO + '\'' +
                ", TIPODOC='" + TIPODOC + '\'' +
                ", COD_TIPODOC='" + COD_TIPODOC + '\'' +
                ", d2_ITEM='" + d2_ITEM + '\'' +
                ", d2_COD='" + d2_COD + '\'' +
                ", d2_QUANT=" + d2_QUANT +
                ", d2_PRCVEN=" + d2_PRCVEN +
                ", d2_TOTAL=" + d2_TOTAL +
                ", IVA21=" + IVA21 +
                ", IVA105=" + IVA105 +
                ", REMITO='" + REMITO + '\'' +
                ", SERREM='" + SERREM + '\'' +
                ", b1_DESC='" + b1_DESC + '\'' +
                ", b1_SEGUM='" + b1_SEGUM + '\'' +
                ", b1_UM='" + b1_UM + '\'' +
                ", letras_ESP='" + letras_ESP + '\'' +
                ", CODBARRAS='" + CODBARRAS + '\'' +
                '}';
    }
}