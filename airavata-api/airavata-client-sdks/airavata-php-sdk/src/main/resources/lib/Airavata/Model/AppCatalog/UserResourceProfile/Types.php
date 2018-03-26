<?php
namespace Airavata\Model\AppCatalog\UserResourceProfile;

/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
use Thrift\Base\TBase;
use Thrift\Type\TType;
use Thrift\Type\TMessageType;
use Thrift\Exception\TException;
use Thrift\Exception\TProtocolException;
use Thrift\Protocol\TProtocol;
use Thrift\Protocol\TBinaryProtocolAccelerated;
use Thrift\Exception\TApplicationException;


/**
 * User specific preferences for a Computer Resource
 * 
 * computeResourceId:
 *   Corelate the preference to a compute resource.
 * 
 * 
 * loginUserName:
 *   If turned true, Airavata will override the preferences of better alternatives exist.
 * 
 * 
 * preferredBatchQueue:
 *  Gateways can choose a defualt batch queue based on average job dimention, reservations or other metrics.
 * 
 * scratchLocation:
 *  Path to the local scratch space on a HPC cluster. Typically used to create working directory for job execution.
 * 
 * allocationProjectNumber:
 *  Typically used on HPC machines to charge computing usage to a account number. For instance, on XSEDE once an
 *    allocation is approved, an allocation number is assigned. Before passing this number with job submittions, the
 *    account to be used has to be added to the allocation.
 * 
 * resourceSpecificCredentialStoreToken:
 *  Resource specific credential store token. If this token is specified, then it is superceeded by the gateway's
 *   default credential store.
 * 
 * validated:
 *  If true the the configuration has been validated in the sense that the username and credential can be used to
 *  login to the remote host and the scratchLocation is a valid location that the user has permission to write to.
 *  Should be treated as read-only and only mutated by Airavata middleware.
 * 
 */
class UserComputeResourcePreference {
  static $_TSPEC;

  /**
   * @var string
   */
  public $computeResourceId = null;
  /**
   * @var string
   */
  public $loginUserName = null;
  /**
   * @var string
   */
  public $preferredBatchQueue = null;
  /**
   * @var string
   */
  public $scratchLocation = null;
  /**
   * @var string
   */
  public $allocationProjectNumber = null;
  /**
   * @var string
   */
  public $resourceSpecificCredentialStoreToken = null;
  /**
   * @var string
   */
  public $qualityOfService = null;
  /**
   * @var string
   */
  public $reservation = null;
  /**
   * @var int
   */
  public $reservationStartTime = null;
  /**
   * @var int
   */
  public $reservationEndTime = null;
  /**
   * @var bool
   */
  public $validated = false;

  public function __construct($vals=null) {
    if (!isset(self::$_TSPEC)) {
      self::$_TSPEC = array(
        1 => array(
          'var' => 'computeResourceId',
          'type' => TType::STRING,
          ),
        2 => array(
          'var' => 'loginUserName',
          'type' => TType::STRING,
          ),
        3 => array(
          'var' => 'preferredBatchQueue',
          'type' => TType::STRING,
          ),
        4 => array(
          'var' => 'scratchLocation',
          'type' => TType::STRING,
          ),
        5 => array(
          'var' => 'allocationProjectNumber',
          'type' => TType::STRING,
          ),
        6 => array(
          'var' => 'resourceSpecificCredentialStoreToken',
          'type' => TType::STRING,
          ),
        7 => array(
          'var' => 'qualityOfService',
          'type' => TType::STRING,
          ),
        8 => array(
          'var' => 'reservation',
          'type' => TType::STRING,
          ),
        9 => array(
          'var' => 'reservationStartTime',
          'type' => TType::I64,
          ),
        10 => array(
          'var' => 'reservationEndTime',
          'type' => TType::I64,
          ),
        11 => array(
          'var' => 'validated',
          'type' => TType::BOOL,
          ),
        );
    }
    if (is_array($vals)) {
      if (isset($vals['computeResourceId'])) {
        $this->computeResourceId = $vals['computeResourceId'];
      }
      if (isset($vals['loginUserName'])) {
        $this->loginUserName = $vals['loginUserName'];
      }
      if (isset($vals['preferredBatchQueue'])) {
        $this->preferredBatchQueue = $vals['preferredBatchQueue'];
      }
      if (isset($vals['scratchLocation'])) {
        $this->scratchLocation = $vals['scratchLocation'];
      }
      if (isset($vals['allocationProjectNumber'])) {
        $this->allocationProjectNumber = $vals['allocationProjectNumber'];
      }
      if (isset($vals['resourceSpecificCredentialStoreToken'])) {
        $this->resourceSpecificCredentialStoreToken = $vals['resourceSpecificCredentialStoreToken'];
      }
      if (isset($vals['qualityOfService'])) {
        $this->qualityOfService = $vals['qualityOfService'];
      }
      if (isset($vals['reservation'])) {
        $this->reservation = $vals['reservation'];
      }
      if (isset($vals['reservationStartTime'])) {
        $this->reservationStartTime = $vals['reservationStartTime'];
      }
      if (isset($vals['reservationEndTime'])) {
        $this->reservationEndTime = $vals['reservationEndTime'];
      }
      if (isset($vals['validated'])) {
        $this->validated = $vals['validated'];
      }
    }
  }

  public function getName() {
    return 'UserComputeResourcePreference';
  }

  public function read($input)
  {
    $xfer = 0;
    $fname = null;
    $ftype = 0;
    $fid = 0;
    $xfer += $input->readStructBegin($fname);
    while (true)
    {
      $xfer += $input->readFieldBegin($fname, $ftype, $fid);
      if ($ftype == TType::STOP) {
        break;
      }
      switch ($fid)
      {
        case 1:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->computeResourceId);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 2:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->loginUserName);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 3:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->preferredBatchQueue);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 4:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->scratchLocation);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 5:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->allocationProjectNumber);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 6:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->resourceSpecificCredentialStoreToken);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 7:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->qualityOfService);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 8:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->reservation);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 9:
          if ($ftype == TType::I64) {
            $xfer += $input->readI64($this->reservationStartTime);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 10:
          if ($ftype == TType::I64) {
            $xfer += $input->readI64($this->reservationEndTime);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 11:
          if ($ftype == TType::BOOL) {
            $xfer += $input->readBool($this->validated);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        default:
          $xfer += $input->skip($ftype);
          break;
      }
      $xfer += $input->readFieldEnd();
    }
    $xfer += $input->readStructEnd();
    return $xfer;
  }

  public function write($output) {
    $xfer = 0;
    $xfer += $output->writeStructBegin('UserComputeResourcePreference');
    if ($this->computeResourceId !== null) {
      $xfer += $output->writeFieldBegin('computeResourceId', TType::STRING, 1);
      $xfer += $output->writeString($this->computeResourceId);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->loginUserName !== null) {
      $xfer += $output->writeFieldBegin('loginUserName', TType::STRING, 2);
      $xfer += $output->writeString($this->loginUserName);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->preferredBatchQueue !== null) {
      $xfer += $output->writeFieldBegin('preferredBatchQueue', TType::STRING, 3);
      $xfer += $output->writeString($this->preferredBatchQueue);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->scratchLocation !== null) {
      $xfer += $output->writeFieldBegin('scratchLocation', TType::STRING, 4);
      $xfer += $output->writeString($this->scratchLocation);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->allocationProjectNumber !== null) {
      $xfer += $output->writeFieldBegin('allocationProjectNumber', TType::STRING, 5);
      $xfer += $output->writeString($this->allocationProjectNumber);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->resourceSpecificCredentialStoreToken !== null) {
      $xfer += $output->writeFieldBegin('resourceSpecificCredentialStoreToken', TType::STRING, 6);
      $xfer += $output->writeString($this->resourceSpecificCredentialStoreToken);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->qualityOfService !== null) {
      $xfer += $output->writeFieldBegin('qualityOfService', TType::STRING, 7);
      $xfer += $output->writeString($this->qualityOfService);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->reservation !== null) {
      $xfer += $output->writeFieldBegin('reservation', TType::STRING, 8);
      $xfer += $output->writeString($this->reservation);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->reservationStartTime !== null) {
      $xfer += $output->writeFieldBegin('reservationStartTime', TType::I64, 9);
      $xfer += $output->writeI64($this->reservationStartTime);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->reservationEndTime !== null) {
      $xfer += $output->writeFieldBegin('reservationEndTime', TType::I64, 10);
      $xfer += $output->writeI64($this->reservationEndTime);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->validated !== null) {
      $xfer += $output->writeFieldBegin('validated', TType::BOOL, 11);
      $xfer += $output->writeBool($this->validated);
      $xfer += $output->writeFieldEnd();
    }
    $xfer += $output->writeFieldStop();
    $xfer += $output->writeStructEnd();
    return $xfer;
  }

}

class UserStoragePreference {
  static $_TSPEC;

  /**
   * @var string
   */
  public $storageResourceId = null;
  /**
   * @var string
   */
  public $loginUserName = null;
  /**
   * @var string
   */
  public $fileSystemRootLocation = null;
  /**
   * @var string
   */
  public $resourceSpecificCredentialStoreToken = null;

  public function __construct($vals=null) {
    if (!isset(self::$_TSPEC)) {
      self::$_TSPEC = array(
        1 => array(
          'var' => 'storageResourceId',
          'type' => TType::STRING,
          ),
        2 => array(
          'var' => 'loginUserName',
          'type' => TType::STRING,
          ),
        3 => array(
          'var' => 'fileSystemRootLocation',
          'type' => TType::STRING,
          ),
        4 => array(
          'var' => 'resourceSpecificCredentialStoreToken',
          'type' => TType::STRING,
          ),
        );
    }
    if (is_array($vals)) {
      if (isset($vals['storageResourceId'])) {
        $this->storageResourceId = $vals['storageResourceId'];
      }
      if (isset($vals['loginUserName'])) {
        $this->loginUserName = $vals['loginUserName'];
      }
      if (isset($vals['fileSystemRootLocation'])) {
        $this->fileSystemRootLocation = $vals['fileSystemRootLocation'];
      }
      if (isset($vals['resourceSpecificCredentialStoreToken'])) {
        $this->resourceSpecificCredentialStoreToken = $vals['resourceSpecificCredentialStoreToken'];
      }
    }
  }

  public function getName() {
    return 'UserStoragePreference';
  }

  public function read($input)
  {
    $xfer = 0;
    $fname = null;
    $ftype = 0;
    $fid = 0;
    $xfer += $input->readStructBegin($fname);
    while (true)
    {
      $xfer += $input->readFieldBegin($fname, $ftype, $fid);
      if ($ftype == TType::STOP) {
        break;
      }
      switch ($fid)
      {
        case 1:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->storageResourceId);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 2:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->loginUserName);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 3:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->fileSystemRootLocation);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 4:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->resourceSpecificCredentialStoreToken);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        default:
          $xfer += $input->skip($ftype);
          break;
      }
      $xfer += $input->readFieldEnd();
    }
    $xfer += $input->readStructEnd();
    return $xfer;
  }

  public function write($output) {
    $xfer = 0;
    $xfer += $output->writeStructBegin('UserStoragePreference');
    if ($this->storageResourceId !== null) {
      $xfer += $output->writeFieldBegin('storageResourceId', TType::STRING, 1);
      $xfer += $output->writeString($this->storageResourceId);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->loginUserName !== null) {
      $xfer += $output->writeFieldBegin('loginUserName', TType::STRING, 2);
      $xfer += $output->writeString($this->loginUserName);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->fileSystemRootLocation !== null) {
      $xfer += $output->writeFieldBegin('fileSystemRootLocation', TType::STRING, 3);
      $xfer += $output->writeString($this->fileSystemRootLocation);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->resourceSpecificCredentialStoreToken !== null) {
      $xfer += $output->writeFieldBegin('resourceSpecificCredentialStoreToken', TType::STRING, 4);
      $xfer += $output->writeString($this->resourceSpecificCredentialStoreToken);
      $xfer += $output->writeFieldEnd();
    }
    $xfer += $output->writeFieldStop();
    $xfer += $output->writeStructEnd();
    return $xfer;
  }

}

/**
 * User Resource Profile
 * 
 * userId:
 * Unique identifier used to link user to corresponding user data model
 * 
 * gatewayID:
 *  Unique identifier for the gateway assigned by Airavata. Corelate this to Airavata Admin API Gateway Registration.
 * 
 * credentialStoreToken:
 *  Gateway's defualt credential store token.
 * 
 * computeResourcePreferences:
 *  List of resource preferences for each of the registered compute resources.
 * 
 *  identityServerTenant:
 * 
 *  identityServerPwdCredToken:
 * 
 * isNull:
 *  Indicates that this instance is just a container for a null value.
 * 
 */
class UserResourceProfile {
  static $_TSPEC;

  /**
   * @var string
   */
  public $userId = null;
  /**
   * @var string
   */
  public $gatewayID = null;
  /**
   * @var string
   */
  public $credentialStoreToken = null;
  /**
   * @var \Airavata\Model\AppCatalog\UserResourceProfile\UserComputeResourcePreference[]
   */
  public $userComputeResourcePreferences = null;
  /**
   * @var \Airavata\Model\AppCatalog\UserResourceProfile\UserStoragePreference[]
   */
  public $userStoragePreferences = null;
  /**
   * @var string
   */
  public $identityServerTenant = null;
  /**
   * @var string
   */
  public $identityServerPwdCredToken = null;
  /**
   * @var bool
   */
  public $isNull = false;

  public function __construct($vals=null) {
    if (!isset(self::$_TSPEC)) {
      self::$_TSPEC = array(
        1 => array(
          'var' => 'userId',
          'type' => TType::STRING,
          ),
        2 => array(
          'var' => 'gatewayID',
          'type' => TType::STRING,
          ),
        3 => array(
          'var' => 'credentialStoreToken',
          'type' => TType::STRING,
          ),
        4 => array(
          'var' => 'userComputeResourcePreferences',
          'type' => TType::LST,
          'etype' => TType::STRUCT,
          'elem' => array(
            'type' => TType::STRUCT,
            'class' => '\Airavata\Model\AppCatalog\UserResourceProfile\UserComputeResourcePreference',
            ),
          ),
        5 => array(
          'var' => 'userStoragePreferences',
          'type' => TType::LST,
          'etype' => TType::STRUCT,
          'elem' => array(
            'type' => TType::STRUCT,
            'class' => '\Airavata\Model\AppCatalog\UserResourceProfile\UserStoragePreference',
            ),
          ),
        6 => array(
          'var' => 'identityServerTenant',
          'type' => TType::STRING,
          ),
        7 => array(
          'var' => 'identityServerPwdCredToken',
          'type' => TType::STRING,
          ),
        8 => array(
          'var' => 'isNull',
          'type' => TType::BOOL,
          ),
        );
    }
    if (is_array($vals)) {
      if (isset($vals['userId'])) {
        $this->userId = $vals['userId'];
      }
      if (isset($vals['gatewayID'])) {
        $this->gatewayID = $vals['gatewayID'];
      }
      if (isset($vals['credentialStoreToken'])) {
        $this->credentialStoreToken = $vals['credentialStoreToken'];
      }
      if (isset($vals['userComputeResourcePreferences'])) {
        $this->userComputeResourcePreferences = $vals['userComputeResourcePreferences'];
      }
      if (isset($vals['userStoragePreferences'])) {
        $this->userStoragePreferences = $vals['userStoragePreferences'];
      }
      if (isset($vals['identityServerTenant'])) {
        $this->identityServerTenant = $vals['identityServerTenant'];
      }
      if (isset($vals['identityServerPwdCredToken'])) {
        $this->identityServerPwdCredToken = $vals['identityServerPwdCredToken'];
      }
      if (isset($vals['isNull'])) {
        $this->isNull = $vals['isNull'];
      }
    }
  }

  public function getName() {
    return 'UserResourceProfile';
  }

  public function read($input)
  {
    $xfer = 0;
    $fname = null;
    $ftype = 0;
    $fid = 0;
    $xfer += $input->readStructBegin($fname);
    while (true)
    {
      $xfer += $input->readFieldBegin($fname, $ftype, $fid);
      if ($ftype == TType::STOP) {
        break;
      }
      switch ($fid)
      {
        case 1:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->userId);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 2:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->gatewayID);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 3:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->credentialStoreToken);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 4:
          if ($ftype == TType::LST) {
            $this->userComputeResourcePreferences = array();
            $_size0 = 0;
            $_etype3 = 0;
            $xfer += $input->readListBegin($_etype3, $_size0);
            for ($_i4 = 0; $_i4 < $_size0; ++$_i4)
            {
              $elem5 = null;
              $elem5 = new \Airavata\Model\AppCatalog\UserResourceProfile\UserComputeResourcePreference();
              $xfer += $elem5->read($input);
              $this->userComputeResourcePreferences []= $elem5;
            }
            $xfer += $input->readListEnd();
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 5:
          if ($ftype == TType::LST) {
            $this->userStoragePreferences = array();
            $_size6 = 0;
            $_etype9 = 0;
            $xfer += $input->readListBegin($_etype9, $_size6);
            for ($_i10 = 0; $_i10 < $_size6; ++$_i10)
            {
              $elem11 = null;
              $elem11 = new \Airavata\Model\AppCatalog\UserResourceProfile\UserStoragePreference();
              $xfer += $elem11->read($input);
              $this->userStoragePreferences []= $elem11;
            }
            $xfer += $input->readListEnd();
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 6:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->identityServerTenant);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 7:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->identityServerPwdCredToken);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 8:
          if ($ftype == TType::BOOL) {
            $xfer += $input->readBool($this->isNull);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        default:
          $xfer += $input->skip($ftype);
          break;
      }
      $xfer += $input->readFieldEnd();
    }
    $xfer += $input->readStructEnd();
    return $xfer;
  }

  public function write($output) {
    $xfer = 0;
    $xfer += $output->writeStructBegin('UserResourceProfile');
    if ($this->userId !== null) {
      $xfer += $output->writeFieldBegin('userId', TType::STRING, 1);
      $xfer += $output->writeString($this->userId);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->gatewayID !== null) {
      $xfer += $output->writeFieldBegin('gatewayID', TType::STRING, 2);
      $xfer += $output->writeString($this->gatewayID);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->credentialStoreToken !== null) {
      $xfer += $output->writeFieldBegin('credentialStoreToken', TType::STRING, 3);
      $xfer += $output->writeString($this->credentialStoreToken);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->userComputeResourcePreferences !== null) {
      if (!is_array($this->userComputeResourcePreferences)) {
        throw new TProtocolException('Bad type in structure.', TProtocolException::INVALID_DATA);
      }
      $xfer += $output->writeFieldBegin('userComputeResourcePreferences', TType::LST, 4);
      {
        $output->writeListBegin(TType::STRUCT, count($this->userComputeResourcePreferences));
        {
          foreach ($this->userComputeResourcePreferences as $iter12)
          {
            $xfer += $iter12->write($output);
          }
        }
        $output->writeListEnd();
      }
      $xfer += $output->writeFieldEnd();
    }
    if ($this->userStoragePreferences !== null) {
      if (!is_array($this->userStoragePreferences)) {
        throw new TProtocolException('Bad type in structure.', TProtocolException::INVALID_DATA);
      }
      $xfer += $output->writeFieldBegin('userStoragePreferences', TType::LST, 5);
      {
        $output->writeListBegin(TType::STRUCT, count($this->userStoragePreferences));
        {
          foreach ($this->userStoragePreferences as $iter13)
          {
            $xfer += $iter13->write($output);
          }
        }
        $output->writeListEnd();
      }
      $xfer += $output->writeFieldEnd();
    }
    if ($this->identityServerTenant !== null) {
      $xfer += $output->writeFieldBegin('identityServerTenant', TType::STRING, 6);
      $xfer += $output->writeString($this->identityServerTenant);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->identityServerPwdCredToken !== null) {
      $xfer += $output->writeFieldBegin('identityServerPwdCredToken', TType::STRING, 7);
      $xfer += $output->writeString($this->identityServerPwdCredToken);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->isNull !== null) {
      $xfer += $output->writeFieldBegin('isNull', TType::BOOL, 8);
      $xfer += $output->writeBool($this->isNull);
      $xfer += $output->writeFieldEnd();
    }
    $xfer += $output->writeFieldStop();
    $xfer += $output->writeStructEnd();
    return $xfer;
  }

}


