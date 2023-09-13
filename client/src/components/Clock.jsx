import React, { useState, useEffect } from "react";
import styled from "styled-components";

const ClockContainer = styled.div`
  width: 100px;
  height: 100px;
  border: 5px solid black;
  border-radius: 50%;
  background-color: white;
  position: absolute;
  top: 30%;
  right: 20%;
`;

const Hand = styled.div`
  position: absolute;
  transform-origin: 50% 100%;
  background-color: #333;
`;

const HourHand = styled(Hand)`
  width: 1%;
  height: 15px;
  transform: ${(props) => `rotate(${props.deg}deg)`};
  top: 50%;
  left: 50%;
`;

const MinuteHand = styled(Hand)`
  width: 0.5%;
  height: 30px;
  transform: ${(props) => `rotate(${props.deg}deg)`};
  top: 50%;
  left: 50%;
`;

const SecondHand = styled(Hand)`
  width: 1px;
  height: 40%;
  transform: ${(props) => `rotate(${props.deg}deg)`};
  top: 50%;
  left: 50%;
  background-color: blue;
`;

export const AnaLogClock = () => {
  const [time, setTime] = useState(new Date());

  useEffect(() => {
    const intervalid = setInterval(() => {
      setTime(new Date());
    }, 1000);

    return () => {
      clearInterval(intervalid);
    };
  }, []);

  const getHandRotation = (unit) => {
    const degree = unit * 6;
    return `rotate(${degree}deg)`;
  };

  const second = time.getSeconds();
  const minute = time.getMinutes() + second / 60;
  const hour = (time.getHours() % 12) + minute / 60;

  return (
    <ClockContainer>
      <HourHand deg={getHandRotation(hour * 5)} />
      <MinuteHand deg={getHandRotation(minute)} />
      <SecondHand deg={getHandRotation(second)} />
    </ClockContainer>
  );
};
