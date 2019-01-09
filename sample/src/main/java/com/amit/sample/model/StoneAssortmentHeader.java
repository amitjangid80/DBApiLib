package com.amit.sample.model;

/**
 * Created by AMIT JANGID on 03-Nov-18.
**/
public class StoneAssortmentHeader
{
    private int code;
    private int userCode;
    private String deviceId;
    private String deviceName;

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public int getUserCode()
    {
        return userCode;
    }

    public void setUserCode(int userCode)
    {
        this.userCode = userCode;
    }

    public String getDeviceId()
    {
        return deviceId;
    }

    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }

    public String getDeviceName()
    {
        return deviceName;
    }

    public void setDeviceName(String deviceName)
    {
        this.deviceName = deviceName;
    }

    /*private int id;
    private int code;
    private int lfyCode;
    private int docGroupCode;

    private int stockLocationValue;
    private String stockLocation;

    private int stockType;
    private String stockTypeDesc;
    private int stoneStockCodeRefValue;
    private String stoneStockCodeRef;

    private int lotNoValue;
    private String lotNo;

    private int stoneTypeValue;
    private String stoneType;

    private int shapeValue;
    private String shape;

    private int gradeNameValue;
    private String gradeName;

    private int qualityValue;

    private String quality;
    private float sizeTo;
    private float sizeFrom;

    private int availablePcs;
    private String remarks;

    private int approvedBy;
    private int approvalStatus;
    private String approvedDate;
    private String approvalReason;

    private int shuffleType;
    private String docNo;
    private int docSrNo;
    private String partyName;
    private int workerValue;
    private String worker;
    private float grnWeight;
    private float hoSelection;

    private float r2Selection;

    private float retCarets;

    private float availableCts;

    private float totalQualityCts;

    private int priceCategoryValue;

    private String priceCategory;

    private String stoneRateDate;

    private int approvalStatusFromSampling;

    private int currencyValue;

    private String currency;

    private String docDate;

    private float exchangeRate;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public int getLfyCode()
    {
        return lfyCode;
    }

    public void setLfyCode(int lfyCode)
    {
        this.lfyCode = lfyCode;
    }

    public int getDocGroupCode()
    {
        return docGroupCode;
    }

    public void setDocGroupCode(int docGroupCode)
    {
        this.docGroupCode = docGroupCode;
    }

    public int getStockLocationValue()
    {
        return stockLocationValue;
    }

    public void setStockLocationValue(int stockLocationValue)
    {
        this.stockLocationValue = stockLocationValue;
    }

    public String getStockLocation()
    {
        return stockLocation;
    }

    public void setStockLocation(String stockLocation)
    {
        this.stockLocation = stockLocation;
    }

    public int getStockType()
    {
        return stockType;
    }

    public void setStockType(int stockType)
    {
        this.stockType = stockType;
    }

    public String getStockTypeDesc()
    {
        return stockTypeDesc;
    }

    public void setStockTypeDesc(String stockTypeDesc)
    {
        this.stockTypeDesc = stockTypeDesc;
    }

    public int getStoneStockCodeRefValue()
    {
        return stoneStockCodeRefValue;
    }

    public void setStoneStockCodeRefValue(int stoneStockCodeRefValue)
    {
        this.stoneStockCodeRefValue = stoneStockCodeRefValue;
    }

    public String getStoneStockCodeRef()
    {
        return stoneStockCodeRef;
    }

    public void setStoneStockCodeRef(String stoneStockCodeRef)
    {
        this.stoneStockCodeRef = stoneStockCodeRef;
    }

    public int getLotNoValue()
    {
        return lotNoValue;
    }

    public void setLotNoValue(int lotNoValue)
    {
        this.lotNoValue = lotNoValue;
    }

    public String getLotNo()
    {
        return lotNo;
    }

    public void setLotNo(String lotNo)
    {
        this.lotNo = lotNo;
    }

    public int getStoneTypeValue()
    {
        return stoneTypeValue;
    }

    public void setStoneTypeValue(int stoneTypeValue)
    {
        this.stoneTypeValue = stoneTypeValue;
    }

    public String getStoneType()
    {
        return stoneType;
    }

    public void setStoneType(String stoneType)
    {
        this.stoneType = stoneType;
    }

    public int getShapeValue()
    {
        return shapeValue;
    }

    public void setShapeValue(int shapeValue)
    {
        this.shapeValue = shapeValue;
    }

    public String getShape()
    {
        return shape;
    }

    public void setShape(String shape)
    {
        this.shape = shape;
    }

    public int getGradeNameValue()
    {
        return gradeNameValue;
    }

    public void setGradeNameValue(int gradeNameValue)
    {
        this.gradeNameValue = gradeNameValue;
    }

    public String getGradeName()
    {
        return gradeName;
    }

    public void setGradeName(String gradeName)
    {
        this.gradeName = gradeName;
    }

    public int getQualityValue()
    {
        return qualityValue;
    }

    public void setQualityValue(int qualityValue)
    {
        this.qualityValue = qualityValue;
    }

    public String getQuality()
    {
        return quality;
    }

    public void setQuality(String quality)
    {
        this.quality = quality;
    }

    public float getSizeTo()
    {
        return sizeTo;
    }

    public void setSizeTo(float sizeTo)
    {
        this.sizeTo = sizeTo;
    }

    public float getSizeFrom()
    {
        return sizeFrom;
    }

    public void setSizeFrom(float sizeFrom)
    {
        this.sizeFrom = sizeFrom;
    }

    public int getAvailablePcs()
    {
        return availablePcs;
    }

    public void setAvailablePcs(int availablePcs)
    {
        this.availablePcs = availablePcs;
    }

    public String getRemarks()
    {
        return remarks;
    }

    public void setRemarks(String remarks)
    {
        this.remarks = remarks;
    }

    public int getApprovedBy()
    {
        return approvedBy;
    }

    public void setApprovedBy(int approvedBy)
    {
        this.approvedBy = approvedBy;
    }

    public int getApprovalStatus()
    {
        return approvalStatus;
    }

    public void setApprovalStatus(int approvalStatus)
    {
        this.approvalStatus = approvalStatus;
    }

    public String getApprovedDate()
    {
        return approvedDate;
    }

    public void setApprovedDate(String approvedDate)
    {
        this.approvedDate = approvedDate;
    }

    public String getApprovalReason()
    {
        return approvalReason;
    }

    public void setApprovalReason(String approvalReason)
    {
        this.approvalReason = approvalReason;
    }

    public int getShuffleType()
    {
        return shuffleType;
    }

    public void setShuffleType(int shuffleType)
    {
        this.shuffleType = shuffleType;
    }

    public String getDocNo()
    {
        return docNo;
    }

    public void setDocNo(String docNo)
    {
        this.docNo = docNo;
    }

    public int getDocSrNo()
    {
        return docSrNo;
    }

    public void setDocSrNo(int docSrNo)
    {
        this.docSrNo = docSrNo;
    }

    public String getPartyName()
    {
        return partyName;
    }

    public void setPartyName(String partyName)
    {
        this.partyName = partyName;
    }

    public int getWorkerValue()
    {
        return workerValue;
    }

    public void setWorkerValue(int workerValue)
    {
        this.workerValue = workerValue;
    }

    public String getWorker()
    {
        return worker;
    }

    public void setWorker(String worker)
    {
        this.worker = worker;
    }

    public float getGrnWeight()
    {
        return grnWeight;
    }

    public void setGrnWeight(float grnWeight)
    {
        this.grnWeight = grnWeight;
    }

    public float getHoSelection()
    {
        return hoSelection;
    }

    public void setHoSelection(float hoSelection)
    {
        this.hoSelection = hoSelection;
    }

    public float getR2Selection()
    {
        return r2Selection;
    }

    public void setR2Selection(float r2Selection)
    {
        this.r2Selection = r2Selection;
    }

    public float getRetCarets()
    {
        return retCarets;
    }

    public void setRetCarets(float retCarets)
    {
        this.retCarets = retCarets;
    }

    public float getAvailableCts()
    {
        return availableCts;
    }

    public void setAvailableCts(float availableCts)
    {
        this.availableCts = availableCts;
    }

    public float getTotalQualityCts()
    {
        return totalQualityCts;
    }

    public void setTotalQualityCts(float totalQualityCts)
    {
        this.totalQualityCts = totalQualityCts;
    }

    public int getPriceCategoryValue()
    {
        return priceCategoryValue;
    }

    public void setPriceCategoryValue(int priceCategoryValue)
    {
        this.priceCategoryValue = priceCategoryValue;
    }

    public String getPriceCategory()
    {
        return priceCategory;
    }

    public void setPriceCategory(String priceCategory)
    {
        this.priceCategory = priceCategory;
    }

    public String getStoneRateDate()
    {
        return stoneRateDate;
    }

    public void setStoneRateDate(String stoneRateDate)
    {
        this.stoneRateDate = stoneRateDate;
    }

    public int getApprovalStatusFromSampling()
    {
        return approvalStatusFromSampling;
    }

    public void setApprovalStatusFromSampling(int approvalStatusFromSampling)
    {
        this.approvalStatusFromSampling = approvalStatusFromSampling;
    }

    public int getCurrencyValue()
    {
        return currencyValue;
    }

    public void setCurrencyValue(int currencyValue)
    {
        this.currencyValue = currencyValue;
    }

    public String getCurrency()
    {
        return currency;
    }

    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    public float getExchangeRate()
    {
        return exchangeRate;
    }

    public void setExchangeRate(float exchangeRate)
    {
        this.exchangeRate = exchangeRate;
    }

    public String getDocDate()
    {
        return docDate;
    }

    public void setDocDate(String docDate)
    {
        this.docDate = docDate;
    }*/
}
