export default function IsSameDay(day) {
  let today = new Date();
  let createdDay = new Date(day);
  if (
    today.getFullYear() === createdDay.getFullYear() &&
    today.getMonth() === createdDay.getMonth() &&
    today.getDate() === createdDay.getDate()
  ) {
    return createdDay.toLocaleTimeString();
  } else if (today.getFullYear() === createdDay.getFullYear()) {
    return `${createdDay.getMonth() + 1}월 ${createdDay.getDate()}일`;
  } else {
    return `${createdDay.getFullYear()}년 ${
      createdDay.getMonth() + 1
    }월 ${createdDay.getDate()}일`;
  }
}
