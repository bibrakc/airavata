<?php
namespace Airavata\Model\AppCatalog\GatewayProfile;

/**
 * Autogenerated by Thrift Compiler (0.9.1)
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


class ComputeResourcePreference {
  static $_TSPEC;

  public $computeResourceId = null;
  public $overridebyAiravata = true;
  public $loginUserName = null;
  public $preferredJobSubmissionProtocol = null;
  public $preferredDataMovementProtocol = null;
  public $preferredBatchQueue = null;
  public $scratchLocation = null;
  public $allocationProjectNumber = null;

  public function __construct($vals=null) {
    if (!isset(self::$_TSPEC)) {
      self::$_TSPEC = array(
        1 => array(
          'var' => 'computeResourceId',
          'type' => TType::STRING,
          ),
        2 => array(
          'var' => 'overridebyAiravata',
          'type' => TType::BOOL,
          ),
        3 => array(
          'var' => 'loginUserName',
          'type' => TType::STRING,
          ),
        4 => array(
          'var' => 'preferredJobSubmissionProtocol',
          'type' => TType::I32,
          ),
        5 => array(
          'var' => 'preferredDataMovementProtocol',
          'type' => TType::I32,
          ),
        6 => array(
          'var' => 'preferredBatchQueue',
          'type' => TType::STRING,
          ),
        7 => array(
          'var' => 'scratchLocation',
          'type' => TType::STRING,
          ),
        8 => array(
          'var' => 'allocationProjectNumber',
          'type' => TType::STRING,
          ),
        );
    }
    if (is_array($vals)) {
      if (isset($vals['computeResourceId'])) {
        $this->computeResourceId = $vals['computeResourceId'];
      }
      if (isset($vals['overridebyAiravata'])) {
        $this->overridebyAiravata = $vals['overridebyAiravata'];
      }
      if (isset($vals['loginUserName'])) {
        $this->loginUserName = $vals['loginUserName'];
      }
      if (isset($vals['preferredJobSubmissionProtocol'])) {
        $this->preferredJobSubmissionProtocol = $vals['preferredJobSubmissionProtocol'];
      }
      if (isset($vals['preferredDataMovementProtocol'])) {
        $this->preferredDataMovementProtocol = $vals['preferredDataMovementProtocol'];
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
    }
  }

  public function getName() {
    return 'ComputeResourcePreference';
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
          if ($ftype == TType::BOOL) {
            $xfer += $input->readBool($this->overridebyAiravata);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 3:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->loginUserName);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 4:
          if ($ftype == TType::I32) {
            $xfer += $input->readI32($this->preferredJobSubmissionProtocol);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 5:
          if ($ftype == TType::I32) {
            $xfer += $input->readI32($this->preferredDataMovementProtocol);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 6:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->preferredBatchQueue);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 7:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->scratchLocation);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 8:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->allocationProjectNumber);
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
    $xfer += $output->writeStructBegin('ComputeResourcePreference');
    if ($this->computeResourceId !== null) {
      $xfer += $output->writeFieldBegin('computeResourceId', TType::STRING, 1);
      $xfer += $output->writeString($this->computeResourceId);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->overridebyAiravata !== null) {
      $xfer += $output->writeFieldBegin('overridebyAiravata', TType::BOOL, 2);
      $xfer += $output->writeBool($this->overridebyAiravata);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->loginUserName !== null) {
      $xfer += $output->writeFieldBegin('loginUserName', TType::STRING, 3);
      $xfer += $output->writeString($this->loginUserName);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->preferredJobSubmissionProtocol !== null) {
      $xfer += $output->writeFieldBegin('preferredJobSubmissionProtocol', TType::I32, 4);
      $xfer += $output->writeI32($this->preferredJobSubmissionProtocol);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->preferredDataMovementProtocol !== null) {
      $xfer += $output->writeFieldBegin('preferredDataMovementProtocol', TType::I32, 5);
      $xfer += $output->writeI32($this->preferredDataMovementProtocol);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->preferredBatchQueue !== null) {
      $xfer += $output->writeFieldBegin('preferredBatchQueue', TType::STRING, 6);
      $xfer += $output->writeString($this->preferredBatchQueue);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->scratchLocation !== null) {
      $xfer += $output->writeFieldBegin('scratchLocation', TType::STRING, 7);
      $xfer += $output->writeString($this->scratchLocation);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->allocationProjectNumber !== null) {
      $xfer += $output->writeFieldBegin('allocationProjectNumber', TType::STRING, 8);
      $xfer += $output->writeString($this->allocationProjectNumber);
      $xfer += $output->writeFieldEnd();
    }
    $xfer += $output->writeFieldStop();
    $xfer += $output->writeStructEnd();
    return $xfer;
  }

}

class GatewayResourceProfile {
  static $_TSPEC;

  public $gatewayID = null;
  public $computeResourcePreferences = null;

  public function __construct($vals=null) {
    if (!isset(self::$_TSPEC)) {
      self::$_TSPEC = array(
        1 => array(
          'var' => 'gatewayID',
          'type' => TType::STRING,
          ),
        2 => array(
          'var' => 'computeResourcePreferences',
          'type' => TType::LST,
          'etype' => TType::STRUCT,
          'elem' => array(
            'type' => TType::STRUCT,
            'class' => '\Airavata\Model\AppCatalog\GatewayProfile\ComputeResourcePreference',
            ),
          ),
        );
    }
    if (is_array($vals)) {
      if (isset($vals['gatewayID'])) {
        $this->gatewayID = $vals['gatewayID'];
      }
      if (isset($vals['computeResourcePreferences'])) {
        $this->computeResourcePreferences = $vals['computeResourcePreferences'];
      }
    }
  }

  public function getName() {
    return 'GatewayResourceProfile';
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
            $xfer += $input->readString($this->gatewayID);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 2:
          if ($ftype == TType::LST) {
            $this->computeResourcePreferences = array();
            $_size0 = 0;
            $_etype3 = 0;
            $xfer += $input->readListBegin($_etype3, $_size0);
            for ($_i4 = 0; $_i4 < $_size0; ++$_i4)
            {
              $elem5 = null;
              $elem5 = new \Airavata\Model\AppCatalog\GatewayProfile\ComputeResourcePreference();
              $xfer += $elem5->read($input);
              $this->computeResourcePreferences []= $elem5;
            }
            $xfer += $input->readListEnd();
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
    $xfer += $output->writeStructBegin('GatewayResourceProfile');
    if ($this->gatewayID !== null) {
      $xfer += $output->writeFieldBegin('gatewayID', TType::STRING, 1);
      $xfer += $output->writeString($this->gatewayID);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->computeResourcePreferences !== null) {
      if (!is_array($this->computeResourcePreferences)) {
        throw new TProtocolException('Bad type in structure.', TProtocolException::INVALID_DATA);
      }
      $xfer += $output->writeFieldBegin('computeResourcePreferences', TType::LST, 2);
      {
        $output->writeListBegin(TType::STRUCT, count($this->computeResourcePreferences));
        {
          foreach ($this->computeResourcePreferences as $iter6)
          {
            $xfer += $iter6->write($output);
          }
        }
        $output->writeListEnd();
      }
      $xfer += $output->writeFieldEnd();
    }
    $xfer += $output->writeFieldStop();
    $xfer += $output->writeStructEnd();
    return $xfer;
  }

}


