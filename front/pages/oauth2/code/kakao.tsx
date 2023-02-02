import React from 'react';
import Spinner from '@/components/common/Spinner';

function kakao() {
  const ACCESS_TOKEN = new URL(window.location.href).searchParams.get(
    'Authorization',
  );
  const REFRESH_TOKEN = new URL(window.location.href).searchParams.get(
    'refreshToken',
  );

  console.log(ACCESS_TOKEN);
  console.log(REFRESH_TOKEN);

  // 예시로 로컬에 저장함
  if (ACCESS_TOKEN) {
    localStorage.setItem('accessToken', ACCESS_TOKEN);
  }
  if (REFRESH_TOKEN) {
    localStorage.setItem('refreshToken', REFRESH_TOKEN);
  }

  return <Spinner />;
}

export default kakao;
